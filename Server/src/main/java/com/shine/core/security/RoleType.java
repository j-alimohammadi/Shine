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
        return type;
    }


    public static RoleType fromType(Integer type) {
        for (RoleType roleType : RoleType.values()) {
            if (Objects.equals(type, roleType.getType()))
                return roleType;
        }
        return null;
    }
}
