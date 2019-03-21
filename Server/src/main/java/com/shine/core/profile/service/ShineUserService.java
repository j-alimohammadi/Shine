package com.shine.core.profile.service;

import com.shine.core.security.domain.ShineUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface ShineUserService {
    ShineUser createNewUser(ShineUser shineUser);

    /**
     * Find user with user name
     *
     * @param userName
     * @return
     */
    Optional<ShineUser> findUserByUserName(String userName);

    /**
     * Find user with user name.
     *
     * @param userName user-name for user
     * @return
     * @throws UsernameNotFoundException in case of user not found
     */
    ShineUser findUserByUserNameNN(String userName) throws UsernameNotFoundException;
}
