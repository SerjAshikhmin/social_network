package com.senla.courses.domain.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    private int id;
    @NonNull
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<MyUserPrincipal> usersPrincipals;

    @Override
    public String getAuthority() {
        return name;
    }
}
