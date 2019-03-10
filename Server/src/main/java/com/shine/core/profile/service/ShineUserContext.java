package com.shine.core.profile.service;

import com.shine.core.security.domain.ShineUser;

/**
 * An holder class that maintain current logged in user.
 *
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface ShineUserContext {
    ShineUser getCurrentLoginUser();

    void setCurrentLoginUser(ShineUser shineUser);
}
