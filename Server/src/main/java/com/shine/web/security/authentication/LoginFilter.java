package com.shine.web.security.authentication;

import com.shine.common.utils.JSONMapper;
import com.shine.web.security.domian.UserCredential;
import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    private final AuthenticationSuccessHandler successHandler;

    public LoginFilter(String filterProcessesUrl, AuthenticationManager authenticationManager,
                       AuthenticationSuccessHandler successHandler) {
        super(filterProcessesUrl);
        setAuthenticationManager(authenticationManager);
        this.successHandler = successHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String requestBody = IOUtils.toString(request.getReader());
        UserCredential credentialBody = JSONMapper.createObjectFromJSON(requestBody, UserCredential.class);

        UsernamePasswordAuthenticationToken authRequest = new
                UsernamePasswordAuthenticationToken(credentialBody.getUserName(), credentialBody.getPassword());

        return getAuthenticationManager().authenticate(authRequest);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authResult);

        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

}                                                               
