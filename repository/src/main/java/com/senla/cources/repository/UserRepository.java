package com.senla.cources.repository;

import com.senla.cources.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserSearchingRepository {

    List<User> getUsersByCountryAndCity(String country, String city);
}
