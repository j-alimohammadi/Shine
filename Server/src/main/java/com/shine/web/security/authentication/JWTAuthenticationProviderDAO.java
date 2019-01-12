package com.shine.web.security.authentication;

import com.shine.web.security.domian.JWTAccessTokenAuthentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("JWTAuthenticationProviderDAO")
public class JWTAuthenticationProviderDAO extends AbstractUserDetailsAuthenticationProvider {

    @Resource(name = "shineUserDetailService")
    private UserDetailsService userDetailsService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JWTAccessTokenAuthentication JWTAccessTokenAuthentication = (JWTAccessTokenAuthentication) authentication;
        final String jwtToken = JWTAccessTokenAuthentication.getToken();


        userDetailsService.loadUserByUsername()


        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JWTAccessTokenAuthentication.class.isAssignableFrom(authentication));
    }
}
