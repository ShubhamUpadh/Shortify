package com.URLShortener.Shortify.Controller;

import com.URLShortener.Shortify.DTO.OriginalRequest;
import com.URLShortener.Shortify.DTO.ShortenRequest;
import com.URLShortener.Shortify.Service.URLService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class URLController {
    URLService urlService;
    public URLController(URLService urlService){
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<String> createShortenedURL(@RequestBody ShortenRequest shortenRequest){
        if (shortenRequest.getOriginalURL() == null || shortenRequest.getOriginalURL().isBlank()) {
            return ResponseEntity.badRequest().body("originalUrl must not be null or blank");
        }
        System.out.println(shortenRequest);
        return urlService.createShortenedURL(shortenRequest.getOriginalURL());
    }

    @GetMapping
    public ResponseEntity<String> getOriginalURL(@RequestBody OriginalRequest originalRequest){
        System.out.println("Fetching originalURL for " + originalRequest.getShortURL());
        return urlService.returnOriginalURL(originalRequest.getShortURL());
    }
}
