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

    public RegistrationFilter(String processingURL,
                              UserInHttpRequest userInHttpRequest,
                              AuthenticationManager authenticationManager) {
        super(processingURL);
        setAuthenticationManager(authenticationManager);

        this.userInHttpRequest = userInHttpRequest;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String requestBody = IOUtils.toString(request.getReader());
        ShineUserDTO shineUserDTO = JSONMapper.createObjectFromJSON(requestBody, ShineUserDTO.class);

        userInHttpRequest.registerNewUser(shineUserDTO);

        UsernamePasswordAuthenticationToken userPassAuthenticationToken =
                new UsernamePasswordAuthenticationToken(shineUserDTO.getLogin(), shineUserDTO.getPassword(), Collections.emptyList());

        //todo : redirect after successfully login
        return getAuthenticationManager().authenticate(userPassAuthenticationToken);
    }


}
