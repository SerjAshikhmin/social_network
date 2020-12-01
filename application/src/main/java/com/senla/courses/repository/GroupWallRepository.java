package com.senla.courses.repository;

import com.senla.courses.domain.GroupWall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupWallRepository extends JpaRepository<GroupWall, Long> {
}
