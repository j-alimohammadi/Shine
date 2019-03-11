package com.shine.core.security;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public enum PermissionType {
    /**
     * Application-specific permission
     */
    SPECIFIC(40);

    private int type;

    PermissionType(final int permissionType) {
        this.type = permissionType;
    }

    public int getType() {
        return type;
    }
}
