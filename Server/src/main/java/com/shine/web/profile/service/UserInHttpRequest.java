package com.shine.web.profile.service;

import com.shine.core.profile.domain.ShineUser;
import com.shine.web.profile.dto.ShineUserDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface UserInHttpRequest {
    ShineUser getUserFromRequest(HttpServletRequest httpServlet);

    ShineUser registerNewUser(ShineUserDTO shineUserDTO);

}
