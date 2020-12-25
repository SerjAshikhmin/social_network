package com.senla.cources.repository;

import com.senla.cources.domain.GroupWallMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupWallMessageRepository extends JpaRepository<GroupWallMessage, Integer> {
}
