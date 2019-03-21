package com.shine.core.profile.dao;

import com.shine.common.persistence.genericDao.DAO;
import com.shine.core.security.domain.ShineUser;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface ShineUserDAO extends DAO<ShineUser> {
    Optional<ShineUser> readUserByUserName(String userName);
}
