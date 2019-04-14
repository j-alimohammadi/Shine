package com.shine.core.security.service.token;

import java.util.Date;
import java.util.UUID;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface TokenStore {
    UUID getSessionId(String token);

    void storeToken(String token, UUID sessionId, Date expirationDate);

    void removeToken(String token);

    void deleteExpiredToken();
}
