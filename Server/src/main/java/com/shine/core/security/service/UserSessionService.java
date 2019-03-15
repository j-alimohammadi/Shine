package com.shine.core.security.service;

import com.shine.core.security.domain.Role;
import com.shine.core.security.domain.ShineUser;
import com.shine.core.security.dto.UserSession;

import java.util.Collection;
import java.util.UUID;

/**
 * Managing {@link UserSession}
 *
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface UserSessionService {
    UserSession createUserSession(ShineUser shineUser, Collection<Role> roles);

    UserSession findSessionById(UUID uuid);

    UserSession removeSession(UUID uuid);
    
}
