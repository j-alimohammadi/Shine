package com.shine.core.profile.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.security.domain.ShineRole;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Repository("roleDAOImpl")
public class RoleDAOImpl extends AbstractDao<ShineRole> implements ShineRoleDAO {
    @Override
    public Set<ShineRole> readRolesByUserName(final String userName) {
        TypedQuery<ShineRole> query = entityManager.createNamedQuery("readRoleByUserName", ShineRole.class);
        query.setParameter("userName", userName);

        return new HashSet<>(query.getResultList());
    }
}
