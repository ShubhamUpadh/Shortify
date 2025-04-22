package com.URLShortener.Shortify.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "access_log")
public class AccessLogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String shortURL;
    private String ipAddress;
    private String userAgent;
    private String referrer;
    private LocalDateTime accessTime;

    public AccessLogModel() {
    }

    public AccessLogModel(String shortURL, String ipAddress, String userAgent, String referrer, LocalDateTime accessTime) {
        this.shortURL = shortURL;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.referrer = referrer;
        this.accessTime = accessTime;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }
}
