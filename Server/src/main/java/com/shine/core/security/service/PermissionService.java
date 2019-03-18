package com.shine.core.security.service;

import com.shine.core.security.PermissionType;
import com.shine.core.security.PermissionValueType;
import com.shine.core.security.domain.Permission;
import com.shine.core.security.domain.ShineRole;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface PermissionService {

    Permission createPermission(PermissionType permissionType, String permissionTarget,
                                PermissionValueType permissionValueType,
                                Integer permissionValue, ShineRole shineRole);
}
