package com.URLShortener.Shortify.DTO;

import java.time.LocalDateTime;

public class ShortenRequest {
    private String originalURL;
    private LocalDateTime expiryTime;

    public String getOriginalURL() {
        return originalURL;
    }
    public LocalDateTime getExpiryTime(){
        return expiryTime;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    @Override
    public String toString() {
        return "this is the shortenRequestObject " + originalURL;
    }

    public void setExpiryTime(LocalDateTime newExpiryTime) {
        this.expiryTime = newExpiryTime;
    }
}
