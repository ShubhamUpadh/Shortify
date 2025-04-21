package com.URLShortener.Shortify.DTO;

public class ShortenRequest {
    private String originalURL;

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    @Override
    public String toString() {
        return "this is the shortenRequestObject " + originalURL;
    }
}
