package com.shine.core.profile.service;

import com.shine.core.profile.domain.ShineUser;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface ShineUserService {
    ShineUser createNewUser(ShineUser shineUser, String password);

    Optional<ShineUser> findUserByUsername(String userName);
}
