package com.shine.core.security.service.authentication;

import com.shine.core.security.domain.JWTAccessTokenAuthentication;
import com.shine.core.security.domain.RegisteredUser;
import com.shine.core.security.service.jwt.JWTTokenService;
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

    protected JWTTokenService jwtTokenService;

    public JWTAuthenticationProvider(UserDetailsService userDetailsService, JWTTokenService jwtTokenService) {
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
        RegisteredUser registeredUser = jwtTokenService.parseToken(jwtToken)
                .orElseThrow(() -> {
                    return new BadCredentialsException("Invalid token");
                });


        return userDetailsService.loadUserByUsername(registeredUser.getLogin());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JWTAccessTokenAuthentication.class.isAssignableFrom(authentication));
    }
}
