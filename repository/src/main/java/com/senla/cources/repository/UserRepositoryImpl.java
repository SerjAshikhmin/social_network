package com.senla.cources.repository;

import com.senla.cources.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

public class UserRepositoryImpl implements UserSearchingRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<User> searchUsers
            (String country, String city, String partOfName, Integer lessThanAgeInYears, Integer moreThanAgeInYears) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> rootUser = cq.from(User.class);

        if (lessThanAgeInYears != null) {
            Predicate lessThanAgeInYearsPredicate = cb.greaterThan(rootUser.get("birthDate"), LocalDate.now().minusYears(lessThanAgeInYears));
            cq.where(cb.and(lessThanAgeInYearsPredicate));
        }

        if (moreThanAgeInYears != null) {
            Predicate moreThanAgeInYearsPredicate = cb.lessThan(rootUser.get("birthDate"), LocalDate.now().minusYears(moreThanAgeInYears));
            cq.where(cb.and(moreThanAgeInYearsPredicate));
        }

        if (country != null) {
            Predicate countryPredicate = cb.equal(rootUser.get("country"), country);
            cq.where(cb.and(countryPredicate));
        }

        if (city != null) {
            Predicate cityPredicate = cb.equal(rootUser.get("city"), city);
            cq.where(cb.and(cityPredicate));
        }

        if (partOfName != null) {
            Predicate partOfFirstNamePredicate = cb.like(rootUser.get("firstName"), "%" + partOfName + "%");
            Predicate partOfLastNamePredicate = cb.like(rootUser.get("lastName"), "%" + partOfName + "%");
            cq.where(cb.and(cb.or(partOfFirstNamePredicate, partOfLastNamePredicate)));
        }

        TypedQuery<User> query = em.createQuery(cq);

        return query.getResultList();
    }
}
