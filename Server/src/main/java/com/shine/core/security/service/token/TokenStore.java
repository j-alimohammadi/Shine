package com.shine.core.security.service.token;

import java.util.UUID;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface TokenStore {
    UUID getSessionId(String token);

    void storeToken(String token, UUID sessionId);

    void removeToken(String token);

    void deleteExpiredToken();
}
