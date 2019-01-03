package com.shine.core.profile.service;

import com.shine.core.profile.dao.ShineUserDAO;
import com.shine.core.profile.domain.ShineUser;
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

    @Transactional
    @Override
    public ShineUser createNewUser(ShineUser shineUser, String password) {
        return null;
    }

    @Transactional
    @Override
    public Optional<ShineUser> findUserByUsername(String userName) {
        return shineUserDAO.readUserByUserName(userName);
    }
}
