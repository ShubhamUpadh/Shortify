package com.URLShortener.Shortify.Service;

import com.URLShortener.Shortify.DTO.ShortenRequest;
import com.URLShortener.Shortify.Model.AccessLogModel;
import com.URLShortener.Shortify.Model.URLModel;
import com.URLShortener.Shortify.Repository.AccessLogRepository;
import com.URLShortener.Shortify.Repository.URLRepository;
import com.URLShortener.Shortify.Utils.CreateAccessLog;
import com.URLShortener.Shortify.Utils.Shortener;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class URLService {
    URLRepository urlRepository;
    Shortener shortener = new Shortener();
    AccessLogRepository accessLogRepository;
    CreateAccessLog createAccessLog;
    public URLService(URLRepository urlRepository, AccessLogRepository accessLogRepository, CreateAccessLog createAccessLog){
        this.urlRepository = urlRepository;
        this.accessLogRepository = accessLogRepository;
        this.createAccessLog = createAccessLog;
    }
    public ResponseEntity<String> createShortenedURL(ShortenRequest shortenThis){
        if (urlRepository.existsByOriginalURL(shortenThis.getOriginalURL())){
            URLModel urlModel = urlRepository.findByOriginalURL(shortenThis.getOriginalURL());
            return ResponseEntity.ok(urlModel.getShortenedURL());
        }

        //user hasn't provided an expiration time
        if (shortenThis.getExpiryTime() == null) shortenThis.setExpiryTime(LocalDateTime.now().plusHours(24));

        //the user provided shortened url doesn't exist
        if (!urlRepository.existsByShortenedURL(shortenThis.getCustomURL())){
            URLModel urlModel = new URLModel(shortenThis.getOriginalURL(), shortenThis.getCustomURL(),
                    0,LocalDateTime.now());
            urlRepository.save(urlModel);
            return ResponseEntity.ok(urlModel.getShortenedURL());
        }

        String shortenedURL = shortener.shorten(shortenThis.getOriginalURL());

        URLModel urlModel = new URLModel(shortenThis.getOriginalURL(), shortenedURL, 0, shortenThis.getExpiryTime());
        urlRepository.save(urlModel);
        return ResponseEntity.ok(shortenedURL);
    }

    @Transactional
    public ResponseEntity<String> returnOriginalURL(String shortenedURL, HttpServletRequest request){
        if (!urlRepository.existsByShortenedURL(shortenedURL)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ShortenedURL not found");
        }
        URLModel urlModel = urlRepository.findByShortenedURL(shortenedURL);
        if (LocalDateTime.now().isAfter(urlModel.getExpiryTime())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ShortenedURL Expired");
        }
        AccessLogModel accessLogModel = createAccessLog.createLog(request, shortenedURL);
        accessLogRepository.save(accessLogModel);
        urlModel.incrementAccessedCount();
        urlRepository.save(urlModel);
        return ResponseEntity.ok(urlModel.getOriginalURL());

    }
}
