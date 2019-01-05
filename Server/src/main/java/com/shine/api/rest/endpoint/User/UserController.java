package com.shine.api.rest.endpoint.User;

import com.shine.api.rest.endpoint.BaseEndpoint;
import com.shine.api.rest.exception.ShineRestException;
import com.shine.core.profile.domain.ShineUser;
import com.shine.core.profile.service.ShineUserService;
import com.shine.web.profile.dto.ShineUserDTO;
import com.shine.web.security.service.JWTTokenService;
import com.shine.web.security.service.LoginService;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@RestController
@RequestMapping(value = "/api/user",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController extends BaseEndpoint {

    @Resource(name = "shineUserServiceImpl")
    protected ShineUserService shineUserService;

    @Resource(name = "shineLoginServiceImpl")
    protected LoginService loginService;

    @Resource(name = "JWTTokenServiceImpl")
    protected JWTTokenService jwtTokenService;


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registerNewUser(@RequestBody ShineUserDTO shineUserDTO) {
        ShineRestException shineRestException;

        Map<String, Object> errorMessage = new LinkedHashMap<>();

        if (StringUtils.isBlank(shineUserDTO.getLogin())) {
            errorMessage.put("login", "Field authenticate should not be empty");
        }


        if (MapUtils.isNotEmpty(errorMessage)) {
            shineRestException = ShineRestException.build(HttpStatus.BAD_REQUEST.value())
                    .addMessage(ShineRestException.INVALID_USER_REGISTRATION_INFO)
                    .addAdditionalData(errorMessage);

            throw shineRestException;
        }


        ShineUser shineUser = ShineUser.ShineUserBuilder.aShineUser()
                .withLogin(shineUserDTO.getLogin())
                .withRegisterTime(new Date())
                .withRepudiation(0)
                .withActiveStatusFlag(true)
                .withUnEncodedPassword(shineUserDTO.getClearTextPassword())
                .build();


        shineUserService.createNewUser(shineUser);

        Authentication authenticate = loginService.authenticate(shineUserDTO.getLogin(),
                shineUserDTO.getClearTextPassword());

        User user = (User) authenticate.getPrincipal();
        List<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String JWTToken = jwtTokenService.generateAuthenticationToken(user.getUsername(), roles);

        return ResponseEntity.ok().header("Authorization", "Bearer " + JWTToken).build();
    }

}
