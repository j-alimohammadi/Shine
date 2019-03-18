package com.shine.core.security.dto;

import com.shine.core.security.PermissionType;
import com.shine.core.security.RoleType;
import com.shine.core.security.domain.ShineRole;
import com.shine.core.security.domain.ShineUser;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class UserSession {
    private UUID id;

    private ShineUser shineUser;

    private Set<String> roles = new HashSet<>();

    private EnumSet<RoleType> roleTypes = EnumSet.noneOf(RoleType.class);


    public UserSession(UUID id, ShineUser shineUser, Set<ShineRole> roles) {
        this.id = id;
        this.shineUser = shineUser;

        roles.forEach(shineRole -> {
            this.roles.add(shineRole.getName());
        });

        roles.forEach(shineRole -> {
            this.roleTypes.add(shineRole.getRoleType());
        });


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

    public boolean isPermitted(final PermissionType permissionType, final String permissionTarget,
                               final Integer permissionValue) {

    }
}
