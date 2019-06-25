package com.shine.core.security.service.jwt;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface TokenService {
    JWTInfo generateAuthenticationToken(String userName, String sessionId);

    JWTInfo parseToken(String jwtToken);
}
