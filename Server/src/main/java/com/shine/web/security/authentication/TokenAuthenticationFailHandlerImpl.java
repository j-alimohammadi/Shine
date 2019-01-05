package com.shine.web.security.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("tokenAuthenticationFailHandlerImpl")
public class TokenAuthenticationFailHandlerImpl implements AuthenticationFailureHandler {
    private final static Logger log = LoggerFactory.getLogger(TokenAuthenticationFailHandlerImpl.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("Authentication failed");


    }
}
