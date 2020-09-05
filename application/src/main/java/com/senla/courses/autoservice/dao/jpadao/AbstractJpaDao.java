package com.senla.courses.autoservice.dao.jpadao;

import com.senla.courses.autoservice.dao.interfaces.IJdbcDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AbstractJpaDao<T> implements IJdbcDao<T> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> clazz;

    public AbstractJpaDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public int insert(T obj) throws PersistenceException {
        entityManager = DbJpaConnector.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(obj);
            transaction.commit();
            entityManager.close();
            return 1;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public int delete(T obj) throws PersistenceException {
        entityManager = DbJpaConnector.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.merge(obj));
            transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return 1;
    }

    @Override
    public int update(T obj) throws PersistenceException {
        entityManager = DbJpaConnector.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(obj);
            transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return 1;
    }

    @Override
    public T findById(int id) throws PersistenceException {
        T obj;
        entityManager = DbJpaConnector.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> objCriteria = criteriaBuilder.createQuery(clazz);
            Root<T> objRoot = objCriteria.from(clazz);
            objCriteria.select(objRoot);
            objCriteria.where(criteriaBuilder.equal(objRoot.get("id"), id));
            obj = entityManager.createQuery(objCriteria).getSingleResult();
            transaction.commit();
            entityManager.close();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        return obj;
    }

    @Override
    public List<T> findAll() throws PersistenceException {
        List<T> allObjects;
        entityManager = DbJpaConnector.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> objCriteria = criteriaBuilder.createQuery(clazz);
            Root<T> objRoot = objCriteria.from(clazz);
            objCriteria.select(objRoot);
            allObjects = entityManager.createQuery(objCriteria).getResultList();
            transaction.commit();
            entityManager.close();

            return allObjects;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
