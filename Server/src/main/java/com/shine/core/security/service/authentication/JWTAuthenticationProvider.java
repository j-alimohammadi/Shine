package com.shine.core.security.service.authentication;

import com.shine.core.security.dto.JWTAccessTokenAuthentication;
import com.shine.core.security.service.jwt.JWTInfo;
import com.shine.core.security.service.jwt.TokenService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
public class JWTAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    protected UserDetailsService userDetailsService;

    protected TokenService jwtTokenService;

    public JWTAuthenticationProvider(UserDetailsService userDetailsService, TokenService jwtTokenService) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JWTAccessTokenAuthentication jwtAccessTokenAuthentication = (JWTAccessTokenAuthentication) authentication;
        final String jwtToken = jwtAccessTokenAuthentication.getToken();

        try {
            JWTInfo jwtInfo = jwtTokenService.parseToken(jwtToken);
            authentication.setDetails(jwtInfo.getSessionId());
            return userDetailsService.loadUserByUsername(jwtInfo.getUserName());
        } catch (RuntimeException ex) {
            throw new BadCredentialsException("Invalid token", ex);
        }


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JWTAccessTokenAuthentication.class.isAssignableFrom(authentication));
    }
}
