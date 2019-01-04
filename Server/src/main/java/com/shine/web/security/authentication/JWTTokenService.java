package com.shine.web.security.authentication;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface JWTTokenService {
    String generateAuthenticationToken(String userName, List<String> roles);
}
