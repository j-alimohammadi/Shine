package com.shine.core.search.dao;

import com.shine.common.persistence.genericDao.DAO;
import com.shine.core.search.domain.SearchField;

import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface SearchFieldDao extends DAO<SearchField> {
    Optional<SearchField> readFieldByAbbreviation(String abbreviation);
}
