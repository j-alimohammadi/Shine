package com.shine.core.security.service.jwt;

import java.util.Date;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class JWTInfo {
    private String tokenValue;

    private Date expirationDate;

    private String sessionId;

    private String userName;


    public JWTInfo(String tokenValue, String sessionId, String userName, Date expirationDate) {
        this.tokenValue = tokenValue;
        this.expirationDate = expirationDate;
        this.sessionId = sessionId;
        this.userName = userName;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUserName() {
        return userName;
    }
}
