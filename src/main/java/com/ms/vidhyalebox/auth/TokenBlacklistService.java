package com.ms.vidhyalebox.auth;


import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {

	private Map<String, Instant> blacklistedTokens = new HashMap<>();
    private static final long TOKEN_EXPIRY_CLEANUP_INTERVAL = 24 * 60 * 60 * 1000; // 24 hours (in ms)

    // Add a token to the blacklist with its expiration time
    public void addToBlacklist(String token, Instant expiryTime) {
        blacklistedTokens.put(token, expiryTime);
    }

    // Check if a token is blacklisted and expired
    public boolean isTokenBlacklisted(String token) {
        if (blacklistedTokens.containsKey(token)) {
            Instant expiryTime = blacklistedTokens.get(token);
            if (Instant.now().isAfter(expiryTime)) {
                blacklistedTokens.remove(token);  // Remove expired token
                return false; // The token is no longer blacklisted
            }
            return true; // Token is still blacklisted
        }
        return false;
    }

    // Periodically clean up expired tokens from the blacklist
    @Scheduled(fixedRate = TOKEN_EXPIRY_CLEANUP_INTERVAL)
    public void cleanupExpiredTokens() {
        Instant now = Instant.now();
        blacklistedTokens.entrySet().removeIf(entry -> entry.getValue().isBefore(now));
    }
}
