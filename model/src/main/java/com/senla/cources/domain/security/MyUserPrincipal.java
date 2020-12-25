package com.senla.cources.domain.security;

import com.senla.cources.domain.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user_principal")
public class MyUserPrincipal implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "id", length = 10)
    private Integer id;
    @NotNull
    @Column(nullable = false, length = 45)
    private String userName;
    @NotNull
    @Column(nullable = false, length = 45)
    private String password;

    @OneToOne(mappedBy = "userPrincipal")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}