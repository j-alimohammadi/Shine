package com.shine.core.security.service.jwt;

import java.util.Date;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class JWTInfo {
    private String tokenValue;

    private Date expirationDate;

    public JWTInfo(String tokenValue, Date expirationDate) {
        this.tokenValue = tokenValue;
        this.expirationDate = expirationDate;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }
}
