package com.shine.core.security;

import com.shine.common.persistence.EnumType;

import java.util.Objects;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public enum RoleType implements EnumType<Integer> {
    STANDARD(0),

    SUPER(10),

    READONLY(20),
    
    DENYING(30);


    private Integer type;

    RoleType(Integer type) {
        this.type = type;
    }

    @Override
    public Integer getType() {
        return null;
    }


    public static RoleType fromType(Integer type) {
        for (RoleType permissionType : RoleType.values()) {
            if (Objects.equals(type, permissionType.getType()))
                return permissionType;
        }
        return null;
    }
}
