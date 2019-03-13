package com.shine.web.profile.service;

import com.shine.core.security.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface UserInHttpRequest {
    User getUserFromRequest(HttpServletRequest httpServlet);

}
