package com.shine.common.persistence.genericDao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public interface DAO<T> {

    T createOrUpdate(T object);

    /**
     * Find item by id and return Optional
     *
     * @param id
     * @return
     */
    Optional<T> find(Serializable id);

    /**
     * Load lazy item with id if not found throws exception
     *
     * @param id
     * @return
     */
    T load(Serializable id);

    List<T> getAll();

    /**
     * Refresh entity from database and sync entity status with database.
     *
     * @param entity
     * @return
     */
    T refresh(T entity);

    void update(T t);

    void delete(T t);

    void deleteById(Serializable id);

    long deleteAll();

    long count();

    boolean exists(Serializable id);
}
