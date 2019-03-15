package com.shine.web.profile.service;

import com.shine.core.security.domain.ShineUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface UserInHttpRequest {
    ShineUser getUserFromRequest(HttpServletRequest httpServlet);

}
