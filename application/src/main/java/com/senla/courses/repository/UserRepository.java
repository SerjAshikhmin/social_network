package com.senla.courses.repository;

import com.senla.courses.domain.Group;
import com.senla.courses.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> getUsersByGroups(Group group);
    List<User> getUsersByCountryAndCity(String country, String city);
    List<User> getUsersByCountry(String country);
}
