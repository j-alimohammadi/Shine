package com.shine.common.persistence.genericDao;

import java.io.Serializable;
import java.util.List;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface DAO<T> {

    T createOrUpdate(T object);

    /**
     * Find item by id and return it or null otherwise
     *
     * @param id
     * @return
     */
    T find(Serializable id);

    /**
     * Load lazy item with id if not found throws exception
     *
     * @param id
     * @return
     */
    T load(Serializable id);

    List<T> getAll();

    void update(T t);

    void delete(T t);

    void deleteById(Serializable id);

    long deleteAll();

    long count();

    boolean exists(Serializable id);
}
