package com.senla.courses.autoservice.dao;

import com.senla.courses.autoservice.dao.interfaces.IGaragePlaceDao;
import com.senla.courses.autoservice.dao.jpadao.AbstractJpaDao;
import com.senla.courses.autoservice.dao.jpadao.DbJpaConnector;
import com.senla.courses.autoservice.model.GaragePlace;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;

public class GaragePlaceDao extends AbstractJpaDao<GaragePlace> implements IGaragePlaceDao {

    public GaragePlaceDao() {
        super(GaragePlace.class);
    }

    @Override
    public int addGaragePlace(GaragePlace garagePlace) throws PersistenceException {
        return insert(garagePlace);
    }

    @Override
    public int removeGaragePlace(GaragePlace garagePlace) throws PersistenceException {
        return delete(garagePlace);
    }

    @Override
    public int updateGaragePlace(GaragePlace garagePlace) throws PersistenceException {
        return update(garagePlace);
    }

    @Override
    public GaragePlace getGaragePlaceById(int garageId, int garagePlaceId) throws PersistenceException {
        GaragePlace garagePlace;
        EntityManager entityManager = DbJpaConnector.getEntityManager();
        entityManager.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<GaragePlace> garagePlaceCriteria = criteriaBuilder.createQuery(GaragePlace.class);
        Root<GaragePlace> garagePlaceRoot = garagePlaceCriteria.from(GaragePlace.class);
        garagePlaceCriteria.select(garagePlaceRoot);
        garagePlaceCriteria.where(criteriaBuilder.equal(garagePlaceRoot.get("id"), garagePlaceId),
                                  criteriaBuilder.equal(garagePlaceRoot.get("garage").get("id"), garageId));
        garagePlace = entityManager.createQuery(garagePlaceCriteria).getSingleResult();
        entityManager.getTransaction().commit();
        entityManager.close();

        return garagePlace;
    }

}
