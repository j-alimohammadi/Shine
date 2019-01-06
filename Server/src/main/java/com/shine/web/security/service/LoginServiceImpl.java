package com.shine.web.security.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("shineLoginServiceImpl")
public class LoginServiceImpl implements LoginService {

    @Resource(name = "shineAuthenticationProvider")
    protected AuthenticationProvider authenticationProvider;

    @Override
    public Authentication authenticate(String userName, String clearTextPassword) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, clearTextPassword);
        Authentication authenticate = authenticationProvider.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return authenticate;
    }


}
