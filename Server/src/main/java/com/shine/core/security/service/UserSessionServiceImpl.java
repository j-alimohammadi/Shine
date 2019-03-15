package com.shine.core.security.service;

import com.shine.core.security.domain.Role;
import com.shine.core.security.domain.ShineUser;
import com.shine.core.security.dto.UserSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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

    @Transactional
    @Override
    public UserSession createUserSession(ShineUser shineUser, Collection<Role> roles) {
        UUID uuid = UUID.randomUUID();
        Set<String> rolesSet = roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        UserSession userSession = new UserSession(uuid, shineUser, rolesSet);

        return uuidToUserSession.put(uuid, userSession);
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
