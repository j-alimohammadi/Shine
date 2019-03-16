package com.shine.core.profile.service;

import com.shine.core.profile.dao.ShineUserDAO;
import com.shine.core.security.domain.ShineUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        // todo: change default enable status flag with a configuration

        final String password = shineUser.getUnEncodedPassword();
        shineUser.setPassword(passwordEncoder.encode(password));

        ShineUser savedShineUser = shineUserDAO.createOrUpdate(shineUser);

        return savedShineUser;
    }

    @Transactional
    @Override
    public Optional<ShineUser> findUserByUserName(String userName) {
        return shineUserDAO.readUserByUserName(userName);
    }

    @Transactional
    @Override
    public ShineUser findUserByUserNameNN(String userName) throws UsernameNotFoundException {
        return findUserByUserName(userName).orElseThrow(() -> {
            return new UsernameNotFoundException(String.format("Username [%s] not found", userName));
        });
    }
}
