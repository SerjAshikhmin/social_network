package com.senla.cources.repository;

import com.senla.cources.domain.security.MyUserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPrincipalRepository extends JpaRepository<MyUserPrincipal, Integer> {

    MyUserPrincipal findMyUserPrincipalByUserName(String userName);
}
