package com.shine.web.security.service;

import org.springframework.security.core.Authentication;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface LoginService {
    Authentication authenticate(String userName, String clearTextPassword);
}
