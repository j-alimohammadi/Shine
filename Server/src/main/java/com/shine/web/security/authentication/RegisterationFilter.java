package com.shine.web.security.authentication;

import com.shine.web.security.domian.AccessTokenAuthentication;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class RegisterationFilter extends AbstractAuthenticationProcessingFilter {
    public RegisterationFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl);

        setAuthenticationManager(authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String authToken = request.getHeader("Authorization").substring(7);
        AccessTokenAuthentication authRequest = new AccessTokenAuthentication(authToken);

        return getAuthenticationManager().authenticate(authRequest);

    }
}
