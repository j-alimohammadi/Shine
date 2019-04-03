package com.shine.core.profile.service;

import com.shine.core.profile.dao.ShineRoleDAO;
import com.shine.core.profile.dao.ShineUserDAO;
import com.shine.core.security.domain.ShineRole;
import com.shine.core.security.domain.ShineUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Set;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("shineUserServiceImpl")
public class ShineUserServiceImpl implements ShineUserService {

    @Resource(name = "shineUserDAOImpl")
    protected ShineUserDAO shineUserDAO;

    @Resource(name = "roleDAOImpl")
    protected ShineRoleDAO shineRoleDAO;


    @Resource(name = "BCryptPasswordEncoder")
    protected PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public ShineUser createNewUser(ShineUser shineUser) {
        return null;
    }

    @Transactional
    @Override
    public ShineUser createNewUser(ShineUser shineUser, String roleName) {
        // todo: change default enable status flag with a configuration property

        final String password = shineUser.getUnEncodedPassword();
        shineUser.setPassword(passwordEncoder.encode(password));

        ShineUser savedShineUser = shineUserDAO.createOrUpdate(shineUser);

        return savedShineUser;
    }

    @Override
    public void updateUser(ShineUser shineUser) {
        shineUserDAO.update(shineUser);
    }

    @Transactional
    @Override
    public Optional<ShineUser> findUserByUserName(String userName) {
        Optional<ShineUser> shineUser = shineUserDAO.readUserByUserName(userName);
        return shineUser;
    }

    @Transactional
    @Override
    public ShineUser findUserByUserNameNN(String userName) throws UsernameNotFoundException {
        return findUserByUserName(userName).orElseThrow(() -> {
            return new UsernameNotFoundException(String.format("Username [%s] not found", userName));
        });
    }

    @Transactional
    @Override
    public Set<ShineRole> findRoleByUserName(String userName) {
        return shineRoleDAO.readRolesByUserName(userName);
    }
}
