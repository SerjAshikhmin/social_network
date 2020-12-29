package com.senla.cources.service.security;

import com.senla.cources.domain.security.MyUserPrincipal;
import com.senla.cources.repository.RoleRepository;
import com.senla.cources.repository.UserPrincipalRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserPrincipalService implements UserDetailsService {

    @Autowired
    private UserPrincipalRepository userPrincipalRepository;
    @Autowired
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    public UserPrincipalService() {
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String userName) throws UsernameNotFoundException {
        UserDetails user = userPrincipalRepository.findMyUserPrincipalByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        }
        UserDetails authUser = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
                .authorities(user.getAuthorities())
                .build();
        return authUser;
    }

    public Collection<? extends GrantedAuthority> getRolesByUser(String userName) {
        UserDetails user = userPrincipalRepository.findMyUserPrincipalByUserName(userName);
        if (user == null) {
            return Collections.emptyList();
        }
        return user.getAuthorities();
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
