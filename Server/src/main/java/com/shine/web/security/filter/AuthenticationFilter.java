package com.shine.web.security.filter;

import com.shine.core.security.domain.JWTAccessTokenAuthentication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    public AuthenticationFilter(String filterProcessesUrl, AuthenticationManager authenticationManager) {
        super(filterProcessesUrl);

        setAuthenticationManager(authenticationManager);
    }


    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        final String header = request.getHeader("Authorization");
        return StringUtils.isNotBlank(header) && header.contains("Bearer ");

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String authToken = request.getHeader("Authorization").substring(7);
        JWTAccessTokenAuthentication authRequest = new JWTAccessTokenAuthentication(authToken);

        return getAuthenticationManager().authenticate(authRequest);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authResult);

        // we do not wand to redirect after authenticate because this is a stateless request
        chain.doFilter(request, response);
    }
}
