package com.shine.core.security;

import com.shine.common.persistence.EnumType;

import java.util.Objects;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public enum PermissionValueType implements EnumType<Integer> {
    /**
     * Boolean type permission that its value can be true or false
     */
    BOOLEAN(10),

    /**
     * Integer type permission type that its value must be greater than
     */
    GREATER_THAN(20),

    /**
     * Integer type permission type that its value must be lesser than
     */
    LESSER_THAN(30),

    /**
     * Integer type permission type that its value must be equal than
     */
    EQUAL(40);


    private Integer type;

    PermissionValueType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static PermissionValueType fromType(Integer type) {
        for (PermissionValueType permissionType : PermissionValueType.values()) {
            if (Objects.equals(type, permissionType.getType()))
                return permissionType;
        }
        return null;
    }
}
