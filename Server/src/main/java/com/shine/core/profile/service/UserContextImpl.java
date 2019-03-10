package com.shine.core.profile.service;

import com.shine.core.security.domain.ShineUser;
import org.springframework.stereotype.Service;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("shineUserContext")
public class UserContextImpl implements ShineUserContext {
    @Override
    public ShineUser getCurrentLoginUser() {
        return null;
    }

    @Override
    public void setCurrentLoginUser(ShineUser shineUser) {

    }
}
