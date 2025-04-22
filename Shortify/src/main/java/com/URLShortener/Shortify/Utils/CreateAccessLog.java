package com.URLShortener.Shortify.Utils;

import com.URLShortener.Shortify.Model.AccessLogModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateAccessLog {
    public AccessLogModel createLog(HttpServletRequest request, String shortURL) {
        AccessLogModel accessLogModel = new AccessLogModel();
        accessLogModel.setIpAddress(getClientIP(request));
        accessLogModel.setUserAgent(request.getHeader("User-Agent"));
        accessLogModel.setAccessTime(LocalDateTime.now());
        accessLogModel.setReferrer(request.getHeader("Referrer"));
        accessLogModel.setShortURL(shortURL);
        return accessLogModel;
    }
    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0]; // In case of multiple IPs, take the first
        }
        return request.getRemoteAddr();
    }
}
