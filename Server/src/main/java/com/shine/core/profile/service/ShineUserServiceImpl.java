package com.shine.core.profile.service;

import com.shine.core.profile.dao.ShineUserDAO;
import com.shine.core.security.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Service("shineUserServiceImpl")
public class ShineUserServiceImpl implements ShineUserService {

    @Resource(name = "shineUserDAOImpl")
    protected ShineUserDAO shineUserDAO;


    @Resource(name = "BCryptPasswordEncoder")
    protected PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User createNewUser(User user) {
        // todo: change default enable status flag with a configuration

        final String password = user.getUnEncodedPassword();
        user.setPassword(passwordEncoder.encode(password));

        User savedUser = shineUserDAO.createOrUpdate(user);

        return savedUser;
    }

    @Transactional
    @Override
    public Optional<User> findUserByUsername(String userName) {
        return shineUserDAO.readUserByUserName(userName);
    }
}
