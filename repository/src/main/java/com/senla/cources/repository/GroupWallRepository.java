package com.senla.cources.repository;

import com.senla.cources.domain.GroupWall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupWallRepository extends JpaRepository<GroupWall, Integer> {
}
