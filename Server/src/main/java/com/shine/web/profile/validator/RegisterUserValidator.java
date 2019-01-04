package com.shine.web.profile.validator;

import com.shine.web.profile.dto.ShineUserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Component("shineRegisterUserValidator")
public class RegisterUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ShineUserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ShineUserDTO shineUserDTO = (ShineUserDTO) target;

        String login = shineUserDTO.getLogin();

    }
}
