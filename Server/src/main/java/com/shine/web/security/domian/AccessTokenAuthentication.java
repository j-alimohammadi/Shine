package com.shine.web.security.domian;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class AccessTokenAuthentication extends UsernamePasswordAuthenticationToken {
    private String token;

    public AccessTokenAuthentication(String token) {
        super(null, null);
        this.token = token;
    }

}
