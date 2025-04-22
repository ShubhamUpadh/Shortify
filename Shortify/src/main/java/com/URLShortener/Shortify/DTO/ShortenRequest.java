package com.URLShortener.Shortify.DTO;

import java.time.LocalDateTime;

public class ShortenRequest {
    private String originalURL;
    private LocalDateTime expiryTime;
    private String customURL;

    public String getOriginalURL() {
        return originalURL;
    }
    public LocalDateTime getExpiryTime(){
        return expiryTime;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public String getCustomURL(){
        return customURL;
    }

    @Override
    public String toString() {
        return "this is the shortenRequestObject " + originalURL;
    }

    public void setExpiryTime(LocalDateTime newExpiryTime) {
        this.expiryTime = newExpiryTime;
    }
}
