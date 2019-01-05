package com.shine.api.rest.endpoint.User;

import com.shine.api.rest.endpoint.BaseEndpoint;
import com.shine.api.rest.exception.ShineRestException;
import com.shine.web.profile.dto.ShineUserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RestController
@RequestMapping(value = "/user",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController extends BaseEndpoint {


    @PostMapping("")
    public ResponseEntity registerNewUser(ShineUserDTO shineUserDTO) {
        if (StringUtils.isBlank(shineUserDTO.getLogin())) {
            throw ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_USER_REGISTRATION_INFO);
        }


        return

    }

}
