package com.shine.core.profile.service;

import com.shine.core.security.domain.ShineUser;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface ShineUserService {
    ShineUser createNewUser(ShineUser shineUser);

    Optional<ShineUser> findUserByUsername(String userName);
}
