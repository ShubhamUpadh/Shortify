package com.URLShortener.Shortify.Service;

import com.URLShortener.Shortify.DTO.ShortenRequest;
import com.URLShortener.Shortify.Model.URLModel;
import com.URLShortener.Shortify.Repository.URLRepository;
import com.URLShortener.Shortify.Utils.Shortener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class URLService {
    URLRepository urlRepository;
    Shortener shortener = new Shortener();
    public URLService(URLRepository urlRepository){
        this.urlRepository = urlRepository;
    }
    public ResponseEntity<String> createShortenedURL(ShortenRequest shortenThis){
        if (urlRepository.existsByOriginalURL(shortenThis.getOriginalURL())){
            URLModel urlModel = urlRepository.findByOriginalURL(shortenThis.getOriginalURL());
            return ResponseEntity.ok(urlModel.getShortenedURL());
        }
        String shortenedURL = shortener.shorten(shortenThis.getOriginalURL());
        URLModel urlModel = new URLModel(shortenThis.getOriginalURL(), shortenedURL, 0, shortenThis.getExpiryTime());
        urlRepository.save(urlModel);
        return ResponseEntity.ok(shortenedURL);
    }
    public ResponseEntity<String> returnOriginalURL(String shortenedURL){
        if (!urlRepository.existsByShortenedURL(shortenedURL)){
            return (ResponseEntity<String>) ResponseEntity.notFound();
        }
        URLModel urlModel = urlRepository.findByShortenedURL(shortenedURL);
        if (LocalDateTime.now().isAfter(urlModel.getExpiryTime()))
        urlModel.incrementAccessedCount();
        urlRepository.save(urlModel);
        return ResponseEntity.ok(urlModel.getOriginalURL());

    }
}
