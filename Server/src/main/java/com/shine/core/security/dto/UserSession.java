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

    private final Map<PermissionType, List<Permission>> permission = new HashMap<>();


    public UserSession(UUID id, ShineUser shineUser, Set<ShineRole> roles) {
        this.id = id;
        this.shineUser = shineUser;
        this.roles.addAll(roles);

        roles.forEach(shineRole -> {
            this.roleTypes.add(shineRole.getRoleType());
        });

        for (PermissionType permissionType : PermissionType.values()) {
            permission.putIfAbsent(permissionType, new ArrayList<>());
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
                               final Integer permissionValue) {
        if (roleTypes.contains(RoleType.SUPER)) {
            return true;
        }

        Optional<Integer> currentValue = permission.get(permissionType)
                .stream()
                .filter(permission1 -> permission1.getTarget().equalsIgnoreCase(permissionTarget))
                .map(permission1 -> permission1.getValue())
                .findAny();

        return !currentValue.isPresent() || currentValue.get() > permissionValue;
    }

    public List<Permission> getPermissionsByType(PermissionType permissionType) {
        return Collections.unmodifiableList(permission.get(permissionType));
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

        Optional<Permission> currentPermission = permission.get(permissionType)
                .stream()
                .filter(permission1 -> {
                    return permission1.getTarget().equals(perm.getTarget());
                }).findFirst();


        if (!currentPermission.isPresent()) {
            permission.get(permissionType).add(perm);
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
