package com.shine.core.security.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.security.domain.Permission;
import org.springframework.stereotype.Repository;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

@Repository("permissionDAOImpl")
public class PermissionDAOImpl extends AbstractDao<Permission> implements PermissionDAO {
}
