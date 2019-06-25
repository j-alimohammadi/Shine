package com.shine.core.security.service.jwt;

import org.junit.Test;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class JWTTokenServiceImplTest {

    private TokenService jwtTokenService = new JWTTokenServiceImpl();

    

    @Test
    public void generateAuthenticationToken() {
        String userName = "john";
        String sessionId = "123";

        JWTInfo jwtInfo = jwtTokenService.generateAuthenticationToken(userName, sessionId);


        
    }

    @Test
    public void parseToken() {
    }
}