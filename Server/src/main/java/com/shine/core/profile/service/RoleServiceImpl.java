package com.shine.core.profile.service;

import com.shine.core.profile.dao.RoleDAO;
import com.shine.core.security.domain.ShineRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Set;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    protected RoleDAO roleDAO;

    @Transactional
    @Override
    public Optional<ShineRole> findRoleByName(String roleName) {
        Optional<ShineRole> shineRole = roleDAO.readRoleByName(roleName);

        return shineRole;
    }

    @Transactional
    @Override
    public ShineRole findRoleByNameNN(String roleName) {
        return findRoleByName(roleName)
                .orElseThrow(IllegalArgumentException::new);
    }


    @Transactional
    @Override
    public Set<ShineRole> findRolesByUserName(String userName) {
        return roleDAO.readRolesByUserName(userName);
    }

}
