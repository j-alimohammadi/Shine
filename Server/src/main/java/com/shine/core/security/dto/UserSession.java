package com.shine.core.security.dto;

import com.shine.core.security.PermissionType;
import com.shine.core.security.PermissionValueType;
import com.shine.core.security.RoleType;
import com.shine.core.security.domain.Permission;
import com.shine.core.security.domain.ShineRole;
import com.shine.core.security.domain.ShineUser;
import org.apache.commons.lang3.StringUtils;

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


        Integer permissionValue = currentPermission
                .map(Permission::getValue)
                .orElse(null);

        // if no permission set, check in role types
        for (RoleType roleType : roleTypes) {
            Integer newValue = roleTypeCheckPermission(roleType);
            if (!Objects.isNull(newValue) && (Objects.isNull(permissionValue) || permissionValue < requestedPermissionValue)) {
                permissionValue = newValue;
            }

        }

        // check additional condition
        final String additionalCondition = currentPermission
                .map(Permission::getAdditionalCondition)
                .orElse(null);

        boolean additionalConditionEvalution = true;
        if (StringUtils.isNotBlank(additionalCondition)) {
            //todo: for now we check only for reputation.
            // refactor this to accept any property and condition
            final long repudiation = Long.valueOf(additionalCondition);

            if (shineUser.getRepudiation() < repudiation) {
                additionalConditionEvalution = false;
            }
        }

        return Objects.isNull(permissionValue) || (requestedPermissionValue < permissionValue && additionalConditionEvalution);
    }

    private Integer roleTypeCheckPermission(RoleType roleType) {
        switch (roleType) {
            case SUPER:
                return Integer.MAX_VALUE;
            case DENYING:
                return 0;
            case READONLY:
                return null;
            case STANDARD:
                return null;
            default:
                return 0;
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

        Optional<Permission> currentPermission = permissions.get(permissionType)
                .stream()
                .filter(permission1 -> {
                    return permission1.getTarget().equals(perm.getTarget());
                }).findFirst();


        if (!currentPermission.isPresent()) {
            permissions.get(permissionType).add(perm);
            return;
        }

    }
}
