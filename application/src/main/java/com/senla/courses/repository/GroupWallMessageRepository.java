package com.senla.courses.repository;

import com.senla.courses.domain.GroupWallMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupWallMessageRepository extends JpaRepository<GroupWallMessage, Long> {
}
