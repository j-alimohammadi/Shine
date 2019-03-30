package com.shine.core.security.service;

import com.shine.core.security.PermissionType;
import com.shine.core.security.domain.Permission;
import com.shine.core.security.domain.ShineRole;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface PermissionService {

    /**
     * Make new permission and assign to Role.
     *
     * @param permissionType
     * @param permissionTarget
     * @param permissionValue
     * @param shineRole
     * @return New persistent permission
     */

    Permission createPermission(PermissionType permissionType, String permissionTarget,
                                Integer permissionValue, ShineRole shineRole);
}
