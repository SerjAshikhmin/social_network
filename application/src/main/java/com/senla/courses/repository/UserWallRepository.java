package com.senla.courses.repository;

import com.senla.courses.domain.UserWall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWallRepository extends JpaRepository<UserWall, Long> {
}
