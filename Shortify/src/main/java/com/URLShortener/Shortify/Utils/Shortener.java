package com.URLShortener.Shortify.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Shortener {

    // Method to generate the shortened URL
    public String shorten(String originalURL) {
        return base62Encode(sha256Hash(originalURL)).substring(0, 8);
    }

    // SHA256 hashing method
    private String sha256Hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return bytesToHex(digest.digest(input.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating SHA-256 hash", e);
        }
    }

    // Convert bytes to hex string
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    // Base62 encoding method
    private String base62Encode(String hexString) {
        final String base62Chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder base62 = new StringBuilder();
        for (byte b : hexString.getBytes()) {
            base62.append(base62Chars.charAt(b & 0x3F)); // Mask byte to 6 bits (Base62 chars)
        }
        return base62.toString();
    }
}
