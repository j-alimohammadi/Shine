package com.shine.core.profile.service;

import com.shine.core.security.domain.ShineRole;

import java.util.Optional;
import java.util.Set;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface RoleService {
    Optional<ShineRole> findRoleByName(String roleName);

    ShineRole findRoleByNameNN(String roleName);

    Set<ShineRole> findRolesByUserName(String userName);
}
