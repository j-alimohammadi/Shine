package com.shine.core.profile.service;

import com.shine.core.security.domain.User;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface ShineUserService {
    User createNewUser(User user);

    Optional<User> findUserByUsername(String userName);
}
