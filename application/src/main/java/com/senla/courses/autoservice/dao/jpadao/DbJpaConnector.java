package com.senla.courses.autoservice.dao.jpadao;

import com.lib.dicontainer.annotations.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Singleton
public class DbJpaConnector {

    private static EntityManagerFactory emFactory;
    private static EntityManager entityManager;

    public DbJpaConnector() {
        emFactory = Persistence.createEntityManagerFactory("com.senla.courses");
    }

    public static EntityManager openSession() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = emFactory.createEntityManager();
        }
        return entityManager;
    }

    public static void closeSession() {
        entityManager.close();
    }

    public static EntityTransaction getTransaction() {
        return openSession().getTransaction();
    }

}
