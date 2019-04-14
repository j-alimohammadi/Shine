package com.shine.common.persistence.genericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/**
 * @author Javad Alimohammadi<bs.alimohammadi@gmail.com>
 */

public abstract class AbstractDao<T> implements DAO<T> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    protected EntityManager entityManager;

    private Class<T> domainClass;

    @SuppressWarnings("unchecked")
    private Class<T> getDomainClass() {
        if (domainClass == null) {
            ParameterizedType thisType = (ParameterizedType) getClass()   //  Gets the current concrete class Class instance.
                    .getGenericSuperclass(); // Get superclass type parameter
            domainClass = (Class<T>) thisType.getActualTypeArguments()[0]; // Gets the array of types mapped into our generics
        }

        return domainClass;
    }

    private String getDomainClassName() {
        Class<T> domainClassTemp = getDomainClass();
        return domainClassTemp.getName();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }


    @Override
    public T createOrUpdate(T object) {
        return entityManager.merge(object);
    }

    @Override
    public Optional<T> find(Serializable id) {
        return Optional.ofNullable(entityManager.find(getDomainClass(), id));
    }

    @Override
    public T load(Serializable id) {
        return entityManager.getReference(getDomainClass(), id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        return entityManager.createQuery("FROM " + getDomainClassName()).getResultList();
    }

    @Override
    public T refresh(T entity) {
        entityManager.refresh(entity);
        return entity;
    }

    @Override
    public void update(T t) {
        entityManager.merge(t);
    }

    @Override
    public void delete(T t) {
        entityManager.remove(t);
    }

    @Override
    public void deleteById(Serializable id) {
        delete(load(id));
    }

    @Override
    public long deleteAll() {
        return entityManager.createQuery("DELETE " + getDomainClassName()).executeUpdate();
    }

    @Override
    public long count() {
        return (Long) entityManager.createQuery("SELECT COUNT(*) FROM " + getDomainClassName()).getSingleResult();
    }

    @Override
    public boolean exists(Serializable id) {
        return find(id) == null;
    }


}

