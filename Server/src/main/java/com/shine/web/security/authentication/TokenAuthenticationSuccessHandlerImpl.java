package com.shine.web.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("tokenAuthenticationSuccessHandlerImpl")
public class TokenAuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Resource(name = "JWTTokenServiceImpl")
    protected JWTTokenService jwtTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        User user = (User) authentication.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(grantedAuthority -> {
                    return grantedAuthority.getAuthority();
                }).collect(Collectors.toList());

        String JWTToken = jwtTokenService.generateAuthenticationToken(user.getUsername(), roles);

        response.addHeader("Authorization", "Bearer " + JWTToken);
    }
}
