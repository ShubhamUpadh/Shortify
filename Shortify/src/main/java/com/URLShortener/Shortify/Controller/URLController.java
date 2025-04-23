package com.URLShortener.Shortify.Controller;

import com.URLShortener.Shortify.DTO.ShortenRequest;
import com.URLShortener.Shortify.Service.URLService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/url")
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
        return urlService.createShortenedURL(shortenRequest);
    }

    @GetMapping("/{shortURL}")
    public ResponseEntity<String> getOriginalURL(@PathVariable String shortURL, HttpServletRequest request){
        System.out.println("Fetching originalURL for " + shortURL);
        return urlService.returnOriginalURL(shortURL, request);
    }
}
