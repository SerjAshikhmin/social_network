package com.senla.courses.autoservice.dao.jpadao;

import com.lib.dicontainer.annotations.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Singleton
public class DbJpaConnector {

    private static EntityManagerFactory emFactory;

    public DbJpaConnector() {
        emFactory = Persistence.createEntityManagerFactory("com.senla.courses");
    }

    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

}
