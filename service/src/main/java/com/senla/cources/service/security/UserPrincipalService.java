package com.senla.cources.service.security;

import com.senla.cources.domain.security.MyUserPrincipal;
import com.senla.cources.repository.RoleRepository;
import com.senla.cources.repository.UserPrincipalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserPrincipalService implements UserDetailsService {

    @Autowired
    private UserPrincipalRepository userPrincipalRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public Collection<? extends GrantedAuthority> getRolesByUser(String userName) {
        return Collections.emptyList();
    }

    public boolean isUserAlreadyExists(String userName) {
        MyUserPrincipal userPrincipal;
        try {
            userPrincipal = userPrincipalRepository.findMyUserPrincipalByUserName(userName);
            System.out.println();
        } catch (Exception e) {
            return false;
        }
        return userPrincipal != null;
    }
}
