package com.shine.core.profile.dao;

import com.shine.common.persistence.genericDao.DAO;
import com.shine.core.security.domain.ShineRole;

import java.util.Set;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface ShineRoleDAO extends DAO<ShineRole> {
    Set<ShineRole> readRolesByUserName(String userName);
}
