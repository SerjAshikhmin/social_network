package com.senla.courses.repository;

import com.senla.courses.domain.security.MyUserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPrincipalRepository extends JpaRepository<MyUserPrincipal, Long> {
}
