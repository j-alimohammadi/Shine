package com.shine.core.security.dto;

import com.shine.core.security.PermissionType;
import com.shine.core.security.PermissionValueType;
import com.shine.core.security.RoleType;
import com.shine.core.security.domain.Permission;
import com.shine.core.security.domain.ShineRole;
import com.shine.core.security.domain.ShineUser;

import java.util.*;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public class UserSession {
    private UUID id;

    private ShineUser shineUser;

    private Set<ShineRole> roles = new HashSet<>();

    private EnumSet<RoleType> roleTypes = EnumSet.noneOf(RoleType.class);

    private final Map<PermissionType, List<Permission>> permissions = new HashMap<>();


    public UserSession(UUID id, ShineUser shineUser, Set<ShineRole> roles) {
        this.id = id;
        this.shineUser = shineUser;
        this.roles.addAll(roles);

        this.roles.forEach(shineRole -> {
            this.roleTypes.add(shineRole.getRoleType());
        });

        for (PermissionType permissionType : PermissionType.values()) {
            permissions.putIfAbsent(permissionType, new ArrayList<>());
        }

        compilePermission();
    }

    public UUID getId() {
        return id;
    }

    public ShineUser getShineUser() {
        return shineUser;
    }

    public boolean isPermitted(PermissionType permissionType, final String permissionTarget,
                               final Integer requestedPermissionValue) {
        if (roleTypes.contains(RoleType.SUPER)) {
            return true;
        }

        Optional<Permission> currentPermission = permissions.get(permissionType)
                .stream()
                .filter(permission1 -> permission1.getTarget().equalsIgnoreCase(permissionTarget))
                .findAny();


        // if no permission set, by default user permitted
        if (!currentPermission.isPresent()) {
            return true;
        }


        final Integer currentPermissionValue = currentPermission.get().getValue();
        if (currentPermission.get().getPermissionValueType().equals(PermissionValueType.BOOLEAN)) {
            return requestedPermissionValue <= currentPermissionValue;
        } else if (currentPermission.get().getPermissionValueType().equals(PermissionValueType.GREATER_THAN)) {
            return requestedPermissionValue > currentPermissionValue;
        } else if (currentPermission.get().getPermissionValueType().equals(PermissionValueType.LESSER_THAN)) {
            return requestedPermissionValue < currentPermissionValue;
        } else {
            throw new UnsupportedOperationException(String.format("PermissionValueType [%s] not support",
                    currentPermission.get().getPermissionValueType()));
        }


    }

    public List<Permission> getPermissionsByType(PermissionType permissionType) {
        return Collections.unmodifiableList(permissions.get(permissionType));
    }

    private void compilePermission() {
        for (ShineRole role : roles) {
            if (role.getRoleType() == RoleType.SUPER) {
                return;
            }
        }

        for (ShineRole role : roles) {
            for (Permission perm : role.getPermissions()) {
                addPermission(perm);
            }
        }
    }


    /**
     * Add permission based on {@link PermissionValueType} value. Permission added based on <b>OR</b> them.<br>
     * <p>
     * If Permission value is {@link PermissionValueType#BOOLEAN} or {@link PermissionValueType#LESSER_THAN} then new
     * value should be greater than existing value.<br>
     * <p>
     * If Permission value is {@link PermissionValueType#GREATER_THAN} then new value should be less than existing
     * value.
     *
     * @param perm New permission value
     */
    private void addPermission(Permission perm) {
        final PermissionType permissionType = perm.getPermissionType();
        final PermissionValueType permissionValueType = perm.getPermissionValueType();

        Optional<Permission> currentPermission = permissions.get(permissionType)
                .stream()
                .filter(permission1 -> {
                    return permission1.getTarget().equals(perm.getTarget());
                }).findFirst();


        if (!currentPermission.isPresent()) {
            permissions.get(permissionType).add(perm);
            return;
        }

        if (permissionValueType.equals(PermissionValueType.BOOLEAN)
                || permissionValueType.equals(PermissionValueType.LESSER_THAN)) {

            if (perm.getValue() > currentPermission.get().getValue()) {
                currentPermission.get().setValue(perm.getValue());
            }
        } else if (permissionValueType.equals(PermissionValueType.GREATER_THAN)) {
            if (perm.getValue() < currentPermission.get().getValue()) {
                currentPermission.get().setValue(perm.getValue());
            }
        } else {
            throw new UnsupportedOperationException(String.format("PermissionValueType [%s] not support",
                    permissionValueType.toString()));
        }

    }
}
