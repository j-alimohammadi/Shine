package com.shine.core.security.service.jwt;

import com.shine.core.security.domain.RegisteredUser;

import java.util.List;
import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface JWTTokenService {
    String generateAuthenticationToken(String userName, List<String> roles);

    Optional<RegisteredUser> parseToken(String jwtToken);
}
