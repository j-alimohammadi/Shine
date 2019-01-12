package com.shine.core.security.service;

import org.springframework.security.authentication.AuthenticationManager;
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

    @Resource(name = "shineAuthenticationManager")
    protected AuthenticationManager authenticationManager;

    @Override
    public Authentication authenticate(String userName, String clearTextPassword) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, clearTextPassword);
        Authentication authenticate = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return authenticate;
    }


}
