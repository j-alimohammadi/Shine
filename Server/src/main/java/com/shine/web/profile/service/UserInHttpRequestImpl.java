package com.shine.web.profile.service;

import com.shine.core.security.domain.ShineUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("shineUserInHttpRequestImpl")
public class UserInHttpRequestImpl implements UserInHttpRequest {

    @Override
    public ShineUser getUserFromRequest(HttpServletRequest httpServlet) {
        return null;
    }

}
