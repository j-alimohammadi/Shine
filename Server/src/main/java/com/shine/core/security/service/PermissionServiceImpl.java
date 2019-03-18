package com.shine.core.security.service;

import com.shine.core.security.PermissionType;
import com.shine.core.security.PermissionValueType;
import com.shine.core.security.dao.PermissionDAO;
import com.shine.core.security.domain.Permission;
import com.shine.core.security.domain.ShineRole;
import com.shine.core.security.exception.InvalidPermissionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

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
                                       PermissionValueType permissionValueType,
                                       Integer permissionValue, ShineRole shineRole) {

        checkPermissionValueIsValid(permissionValueType, permissionValue);


        Permission permission = new Permission();
        permission.setPermissionType(permissionType);
        permission.setTarget(permissionTarget);
        permission.setPermissionValueType(permissionValueType);
        permission.setValue(permissionValue);
        permission.setShineRole(shineRole);


        Permission createdPermission = permissionDAO.createOrUpdate(permission);

        return createdPermission;
    }


    /**
     * Check permission value according to permission type.<br>
     * if {@code permissionValueType} is
     * {@link PermissionValueType#BOOLEAN} then {@code permissionValue} must be <b>0</b> (false), <b>1</b> (true) or
     * <b>null</b>, otherwise it must be any valid integer number <b>not null</b>.
     *
     * @param permissionValueType Type of permission value
     * @param permissionValue     Value of permission
     * @throws {@link InvalidPermissionException} if permission value is invalid
     */
    private void checkPermissionValueIsValid(PermissionValueType permissionValueType, final Integer permissionValue) {

        if (permissionValueType.equals(PermissionValueType.BOOLEAN)) {
            if (!permissionValue.equals(0) && !permissionValue.equals(1)) {
                throw new InvalidPermissionException(String.format("[%s] is invalid for PermissionValueType.BOOLEAN", permissionValue));
            }
        }

        if (permissionValueType.equals(PermissionValueType.EQUAL) ||
                permissionValueType.equals(PermissionValueType.GREATER_THAN) ||
                permissionValueType.equals(PermissionValueType.LESSER_THAN)) {

            if (Objects.isNull(permissionValue)) {
                throw new InvalidPermissionException("null is invalid for PermissionValueType.EQUAL|GREATER_THAN|LESSER_THAN");
            }

        }


    }
}
