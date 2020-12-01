package com.senla.courses.repository;

import com.senla.courses.domain.UserWallMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWallMessageRepository extends JpaRepository<UserWallMessage, Long> {
}
