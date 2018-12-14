package com.shine.core.profile.service;

import com.shine.core.profile.domain.ShineUser;

/**
 * An holder class that maintain current logged in user.
 *
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface UserContext {
    ShineUser getCurrentLoginUser();

    void setCurrentLoginUser(ShineUser shineUser);
}
