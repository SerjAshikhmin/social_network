package com.senla.courses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user_principal")
public class UserPrincipal implements UserDetails {

    @Id
    private int id;
    @NonNull
    private String userName;
    @NonNull
    private String password;

    @OneToOne(mappedBy = "userPrincipal")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
