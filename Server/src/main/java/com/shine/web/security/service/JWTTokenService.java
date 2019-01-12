package com.shine.web.security.service;

import com.shine.web.security.domian.WebUser;

import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface JWTTokenService {
    String generateAuthenticationToken(String userName, List<String> roles);

    WebUser parseToken(String jwtToken);
}
