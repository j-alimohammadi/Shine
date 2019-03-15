package com.shine.core.security.service.jwt;

import com.shine.core.security.dto.RegisteredUser;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface JWTTokenService {
    JWTInfo generateAuthenticationToken(String userName, String sessionId);

    Optional<RegisteredUser> parseToken(String jwtToken);
}
