package com.shine.core.profile.service;

import com.shine.common.utils.ThreadLocalManager;
import com.shine.core.security.domain.ShineUser;
import org.springframework.stereotype.Service;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("shineUserContext")
public class UserSessionSourceImpl implements UserSessionSource {

    private final ThreadLocal<UserSessionSource> USER_CONTEXT = ThreadLocalManager.createThreadLocal(UserSessionSource.class);


    @Override
    public ShineUser getCurrentLoginUser() {
        return null;
    }

    @Override
    public void setCurrentLoginUser(ShineUser shineUser) {

    }
}
