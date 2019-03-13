package com.shine.core.profile.service;

import com.shine.common.utils.ThreadLocalManager;
import com.shine.core.security.domain.User;
import org.springframework.stereotype.Service;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("shineUserContext")
public class UserSessionSourceImpl implements UserSessionSource {

    private final ThreadLocal<UserSessionSource> USER_CONTEXT = ThreadLocalManager.createThreadLocal(UserSessionSource.class);


    @Override
    public User getCurrentLoginUser() {
        return null;
    }

    @Override
    public void setCurrentLoginUser(User user) {

    }
}
