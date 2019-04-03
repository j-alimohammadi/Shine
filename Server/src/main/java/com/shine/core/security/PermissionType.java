package com.shine.core.security;

import com.shine.common.persistence.EnumType;

import java.util.Objects;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public enum PermissionType implements EnumType<Integer> {


    /**
     * Application-specific permission
     */
    SPECIFIC(40);

    private int type;

    PermissionType(final int permissionType) {
        this.type = permissionType;
    }

    @Override
    public Integer getType() {
        return type;
    }

    public static PermissionType fromType(int type) {
        for (PermissionType permissionType : PermissionType.values()) {
            if (Objects.equals(type, permissionType.getType()))
                return permissionType;
        }
        return null;
    }
}
