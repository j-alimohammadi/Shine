package com.shine.api.rest.endpoint.user;

import com.shine.api.rest.endpoint.BaseEndpoint;
import com.shine.api.rest.exception.ShineRestException;
import com.shine.core.profile.domain.ShineUser;
import com.shine.core.profile.service.ShineUserService;
import com.shine.core.security.service.LoginService;
import com.shine.web.profile.dto.ShineUserDTO;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RestController
@RequestMapping(value = "/api/user",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserManagementController extends BaseEndpoint {

    @Resource(name = "shineUserServiceImpl")
    protected ShineUserService shineUserService;

    @Resource(name = "shineLoginServiceImpl")
    protected LoginService loginService;


    @Resource(name = "tokenAuthenticationSuccessHandlerImpl")
    protected AuthenticationSuccessHandler authenticationSuccessHandler;


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registerNewUser(@RequestBody ShineUserDTO shineUserDTO,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws IOException, ServletException {
        ShineRestException shineRestException;

        Map<String, Object> errorMessage = new LinkedHashMap<>();

        if (StringUtils.isBlank(shineUserDTO.getLogin())) {
            errorMessage.put("login", "'login' field should not be empty");
        }

        if (!GenericValidator.isEmail(shineUserDTO.getEmail())) {
            errorMessage.put("email", "'email' field is invalid");
        }


        if (MapUtils.isNotEmpty(errorMessage)) {
            shineRestException = ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_USER_REGISTRATION_INFO)
                    .addAdditionalData(errorMessage);

            throw shineRestException;
        }


        ShineUser shineUser = ShineUser.ShineUserBuilder.aShineUser()
                .withLogin(shineUserDTO.getLogin())
                .withEmail(shineUserDTO.getEmail())
                .withRegisterTime(new Date())
                .withRepudiation(0)
                .withActiveStatusFlag(true)
                .withUnEncodedPassword(shineUserDTO.getClearTextPassword())
                .build();


        shineUserService.createNewUser(shineUser);

        Authentication authenticate = loginService.authenticate(shineUserDTO.getLogin(),
                shineUserDTO.getClearTextPassword());

        authenticationSuccessHandler.onAuthenticationSuccess(request, response, authenticate);

        return ResponseEntity.ok().build();
    }

}
