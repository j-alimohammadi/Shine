package com.shine.web.profile.service;

import com.shine.core.profile.domain.ShineUser;
import com.shine.core.profile.service.ShineUserService;
import com.shine.web.profile.dto.ShineUserDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("shineUserInHttpRequestImpl")
public class UserInHttpRequestImpl implements UserInHttpRequest {

    @Resource(name = "shineUserServiceImpl")
    protected ShineUserService shineUserService;

    @Override
    public ShineUser getUserFromRequest(HttpServletRequest httpServlet) {
        return null;
    }

    @Override
    public ShineUser registerNewUser(ShineUserDTO shineUserDTO) {
        //todo: validation user details

        ShineUser shineUser = ShineUser.ShineUserBuilder.aShineUser()
                .withLogin(shineUserDTO.getLogin())
                .withRegisterTime(new Date())
                .withRepudiation(0)
                .withUnEncodedPassword(shineUserDTO.getPassword())
                .build();

        return shineUserService.createNewUser(shineUser);
    }

}
