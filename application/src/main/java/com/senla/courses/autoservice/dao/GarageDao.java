package com.senla.courses.autoservice.dao;

import com.senla.courses.autoservice.dao.interfaces.IGarageDao;
import com.senla.courses.autoservice.dao.jpadao.AbstractJpaDao;
import com.senla.courses.autoservice.dao.jpadao.DbJpaConnector;
import com.senla.courses.autoservice.model.Garage;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GarageDao extends AbstractJpaDao<Garage> implements IGarageDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int addGarage(Garage garage) throws PersistenceException {
        return insert(garage);
    }

    @Override
    public int removeGarage(Garage garage) throws PersistenceException {
        return delete(garage);
    }

    @Override
    public Garage getGarageById(int id) throws PersistenceException {
        Garage garage;
        entityManager = DbJpaConnector.openSession();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Garage> objCriteria = criteriaBuilder.createQuery(Garage.class);
        Root<Garage> objRoot = objCriteria.from(Garage.class);
        objCriteria.select(objRoot);
        objCriteria.where(criteriaBuilder.equal(objRoot.get("id"), id));
        garage = entityManager.createQuery(objCriteria).getSingleResult();
        Hibernate.initialize(garage.getGaragePlaces());
        DbJpaConnector.closeSession();

        return garage;
    }

    @Override
    public List<Garage> getAllGarages() throws PersistenceException {
        return findAll();
    }

    @Override
    public List<Garage> findAll() throws PersistenceException {
        List<Garage> allGarages;
        entityManager = DbJpaConnector.openSession();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Garage> objCriteria = criteriaBuilder.createQuery(Garage.class);
        Root<Garage> objRoot = objCriteria.from(Garage.class);
        objCriteria.select(objRoot);
        allGarages = entityManager.createQuery(objCriteria).getResultList();
        for (Garage garage : allGarages) {
            Hibernate.initialize(garage.getGaragePlaces());
        }
        DbJpaConnector.closeSession();

        return allGarages;
    }

    @Override
    public void setAllGarages(List<Garage> allGarages) {
        //this.garages = allGarages;
    }

    @Override
    public int updateGarage(Garage garage) throws PersistenceException {
        return update(garage);
    }

}
