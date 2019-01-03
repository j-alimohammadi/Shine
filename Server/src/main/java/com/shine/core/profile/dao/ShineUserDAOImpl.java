package com.shine.core.profile.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.profile.domain.ShineUser;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Repository("shineUserDAOImpl")
public class ShineUserDAOImpl extends AbstractDao<ShineUser> implements ShineUserDAO {

    @Override
    public Optional<ShineUser> readUserByUserName(final String login) {
        Session hibernateSession = entityManager.unwrap(Session.class);
        Optional<ShineUser> userOptional = hibernateSession.byNaturalId(ShineUser.class)
                .using("login", login)
                .loadOptional();

        return userOptional;
    }
}
