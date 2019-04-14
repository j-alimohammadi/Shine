package com.shine.core.security.service.login;

import org.springframework.security.core.Authentication;

/**
 * Can be used when we want manually register user in system for example
 * after user successfully registered in application
 *
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface LoginService {
    /**
     * Authenticate user in platform
     *
     * @param userName
     * @param clearTextPassword
     * @return
     */
    Authentication authenticate(String userName, String clearTextPassword);
}
