package com.shine.web.profile.service;

import com.shine.core.profile.domain.ShineUser;
import com.shine.core.profile.service.ShineUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
