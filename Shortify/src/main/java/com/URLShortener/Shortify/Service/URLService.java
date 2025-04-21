package com.URLShortener.Shortify.Service;

import com.URLShortener.Shortify.Model.URLModel;
import com.URLShortener.Shortify.Repository.URLRepository;
import com.URLShortener.Shortify.Utils.Shortener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class URLService {
    URLRepository urlRepository;
    Shortener shortener = new Shortener();
    public URLService(URLRepository urlRepository){
        this.urlRepository = urlRepository;
    }
    public ResponseEntity<String> createShortenedURL(String originalURL){
        if (urlRepository.existsByOriginalURL(originalURL)){
            URLModel urlModel = urlRepository.findByOriginalURL(originalURL);
            return ResponseEntity.ok(urlModel.getShortenedURL());
        }
        String shortenedURL = shortener.shorten(originalURL);
        System.out.println("_________________________");
        System.out.println(shortenedURL);
        System.out.println("_________________________");
        URLModel urlModel = new URLModel(originalURL, shortenedURL, 0);
        urlRepository.save(urlModel);
        return ResponseEntity.ok(shortenedURL);
    }
    public ResponseEntity<String> returnOriginalURL(String shortenedURL){
        if (!urlRepository.existsByShortenedURL(shortenedURL)){
            return (ResponseEntity<String>) ResponseEntity.notFound();
        }
        URLModel urlModel = urlRepository.findByShortenedURL(shortenedURL);
        urlModel.incrementAccessedCount();
        urlRepository.save(urlModel);
        return ResponseEntity.ok(urlModel.getOriginalURL());

    }
}
