package com.shine.web.security.authentication;

import com.shine.common.utils.JSONMapper;
import com.shine.web.profile.dto.ShineUserDTO;
import com.shine.web.profile.service.UserInHttpRequest;
import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class RegistrationFilter extends AbstractAuthenticationProcessingFilter {

    protected UserInHttpRequest userInHttpRequest;
    protected AuthenticationSuccessHandler authenticationSuccessHandler;
    protected AuthenticationFailureHandler authenticationFailureHandler;

    public RegistrationFilter(String processingURL,
                              UserInHttpRequest userInHttpRequest,
                              AuthenticationSuccessHandler authenticationSuccessHandler,
                              AuthenticationFailureHandler authenticationFailureHandler,
                              AuthenticationManager authenticationManager) {
        super(processingURL);
        setAuthenticationManager(authenticationManager);
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.userInHttpRequest = userInHttpRequest;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String requestBody = IOUtils.toString(request.getReader());
        ShineUserDTO shineUserDTO = JSONMapper.createObjectFromJSON(requestBody, ShineUserDTO.class);

        userInHttpRequest.registerNewUser(shineUserDTO);

        UsernamePasswordAuthenticationToken userPassAuthenticationToken =
                new UsernamePasswordAuthenticationToken(shineUserDTO.getLogin(), shineUserDTO.getPassword(), Collections.emptyList());

        return getAuthenticationManager().authenticate(userPassAuthenticationToken);
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        authenticationFailureHandler.onAuthenticationFailure(request, response, failed);
    }
}
