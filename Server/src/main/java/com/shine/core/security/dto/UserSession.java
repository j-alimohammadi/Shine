package com.shine.core.security.dto;

import com.shine.core.security.domain.ShineUser;

import java.util.Set;
import java.util.UUID;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class UserSession {
    private UUID id;
    private ShineUser shineUser;
    private Set<String> roles;


    public UserSession(UUID id, ShineUser shineUser, Set<String> roles) {
        this.id = id;
        this.shineUser = shineUser;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public ShineUser getShineUser() {
        return shineUser;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
