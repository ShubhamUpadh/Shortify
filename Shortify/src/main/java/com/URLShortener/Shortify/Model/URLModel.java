package com.URLShortener.Shortify.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

@Entity
@Table(name = "url")
public class URLModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String originalURL;
    private String shortenedURL;
    private int accesedCount;
    private LocalDateTime expiryTime;

    public URLModel() {
    }

    public URLModel(String originalURL, String shortenedURL, int accesedCount, LocalDateTime expiryTime) {
        this.originalURL = originalURL;
        this.shortenedURL = shortenedURL;
        this.accesedCount = accesedCount;
        this.expiryTime = expiryTime;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public String getShortenedURL() {
        return shortenedURL;
    }

    public void setShortenedURL(String shortenedURL) {
        this.shortenedURL = shortenedURL;
    }

    public int getAccesedCount() {
        return accesedCount;
    }

    public void setAccesedCount(int accesedCount) {
        this.accesedCount = accesedCount;
    }

    public void incrementAccessedCount(){
        this.accesedCount++;
    }

    public LocalDateTime getExpiryTime() {
        return this.expiryTime;
    }
}
