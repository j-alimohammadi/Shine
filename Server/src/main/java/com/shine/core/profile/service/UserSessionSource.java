package com.shine.core.profile.service;

import com.shine.core.security.domain.User;

/**
 * An holder class that maintain current logged in user.
 *
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface UserSessionSource {
    User getCurrentLoginUser();

    void setCurrentLoginUser(User user);
}
