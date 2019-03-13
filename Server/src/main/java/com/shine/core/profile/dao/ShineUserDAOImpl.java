package com.shine.core.profile.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.security.domain.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Repository("shineUserDAOImpl")
public class ShineUserDAOImpl extends AbstractDao<User> implements ShineUserDAO {

    @Override
    public Optional<User> readUserByUserName(final String login) {
        Session hibernateSession = entityManager.unwrap(Session.class);
        Optional<User> userOptional = hibernateSession.byNaturalId(User.class)
                .using("login", login)
                .loadOptional();

        return userOptional;
    }
}
