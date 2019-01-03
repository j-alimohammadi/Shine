package com.shine.core.profile.service;

import com.shine.core.profile.dao.ShineUserDAO;
import com.shine.core.profile.domain.ShineUser;
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
    public ShineUser createNewUser(ShineUser shineUser) {
        final String password = shineUser.getUnEncodedPassword();
        shineUser.setPassword(passwordEncoder.encode(password));

        ShineUser savedUser = shineUserDAO.createOrUpdate(shineUser);

        return savedUser;
    }

    @Transactional
    @Override
    public Optional<ShineUser> findUserByUsername(String userName) {
        return shineUserDAO.readUserByUserName(userName);
    }
}
