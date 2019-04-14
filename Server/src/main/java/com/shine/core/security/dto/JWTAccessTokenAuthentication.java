package com.shine.core.security.dto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class JWTAccessTokenAuthentication extends UsernamePasswordAuthenticationToken {
    private String token;

    public JWTAccessTokenAuthentication(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
