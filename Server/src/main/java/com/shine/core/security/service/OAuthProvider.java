package com.shine.core.security.service;

import com.shine.common.persistence.EnumType;

import java.util.Objects;

public enum OAuthProvider implements EnumType<Integer> {
    GOOGLE(1);


    private int type;

    OAuthProvider(final int providerType) {
        this.type = providerType;
    }

    

    @Override
    public Integer getType() {
        return type;
    }

    public static OAuthProvider fromType(Integer type) {
        for (OAuthProvider permissionType : OAuthProvider.values()) {
            if (Objects.equals(type, permissionType.getType()))
                return permissionType;
        }
        return null;
    }

}
