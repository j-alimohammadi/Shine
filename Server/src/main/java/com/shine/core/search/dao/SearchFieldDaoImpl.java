package com.shine.core.search.dao;

import com.shine.common.persistence.genericDao.AbstractDao;
import com.shine.core.search.domain.SearchField;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */
@Repository("searchFieldDaoImpl")
public class SearchFieldDaoImpl extends AbstractDao<SearchField> implements SearchFieldDao {

    @Override
    public Optional<SearchField> readFieldByAbbreviation(String abbreviation) {
        Session hibernateSession = entityManager.unwrap(Session.class);
        return hibernateSession.byNaturalId(SearchField.class)
                .using("abbreviation", abbreviation)
                .loadOptional();
    }
}
