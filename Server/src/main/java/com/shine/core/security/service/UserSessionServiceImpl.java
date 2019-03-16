package com.shine.core.security.service;

import com.shine.core.security.domain.ShineRole;
import com.shine.core.security.domain.ShineUser;
import com.shine.core.security.dto.UserSession;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("userSessionServiceImpl")
public class UserSessionServiceImpl implements UserSessionService {
    private final ConcurrentHashMap<UUID, UserSession> uuidToUserSession = new ConcurrentHashMap<>();




    @Override
    public UserSession createUserSession(ShineUser shineUser) {
        UUID sessionId = UUID.randomUUID();
        return createUserSession(sessionId, shineUser);
    }


    @Override
    public UserSession createUserSession(UUID sessionId, ShineUser shineUser) {
        Set<String> rolesSet = shineUser.getShineRoles().stream()
                .map(ShineRole::getName)
                .collect(Collectors.toSet());

        UserSession userSession = new UserSession(sessionId, shineUser, rolesSet);

        uuidToUserSession.put(sessionId, userSession);
        return userSession;
    }

    @Override
    public UserSession findSessionById(UUID uuid) {
        return uuidToUserSession.get(uuid);
    }

    @Override
    public UserSession removeSession(UUID uuid) {
        return uuidToUserSession.remove(uuid);
    }


}
