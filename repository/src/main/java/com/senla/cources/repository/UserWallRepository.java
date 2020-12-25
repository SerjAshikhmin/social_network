package com.senla.cources.repository;

import com.senla.cources.domain.UserWall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWallRepository extends JpaRepository<UserWall, Integer> {
}
