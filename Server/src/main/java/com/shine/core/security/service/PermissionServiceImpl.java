package com.shine.core.security.service;

import com.shine.core.security.PermissionType;
import com.shine.core.security.dao.PermissionDAO;
import com.shine.core.security.domain.Permission;
import com.shine.core.security.domain.ShineRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    protected PermissionDAO permissionDAO;

    @Transactional
    @Override
    public Permission createPermission(PermissionType permissionType, String permissionTarget,
                                       Integer permissionValue, ShineRole shineRole) {

        Permission permission = new Permission();
        permission.setPermissionType(permissionType);
        permission.setTarget(permissionTarget);
        permission.setValue(permissionValue);
        permission.setShineRole(shineRole);


        Permission createdPermission = permissionDAO.createOrUpdate(permission);

        return createdPermission;
    }


}
