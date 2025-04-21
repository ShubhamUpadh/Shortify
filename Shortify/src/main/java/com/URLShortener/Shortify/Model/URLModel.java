package com.URLShortener.Shortify.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class URLModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String originalURL;
    private String shortenedURL;
    private int accesedCount;

    public URLModel() {
    }

    public URLModel(String originalURL, String shortenedURL, int accesedCount) {
        this.originalURL = originalURL;
        this.shortenedURL = shortenedURL;
        this.accesedCount = accesedCount;
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
}
