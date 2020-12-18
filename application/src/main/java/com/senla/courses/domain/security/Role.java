package com.senla.courses.domain.security;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Length(min = 2)
    @NotNull
    @Column(nullable = false, length = 45)
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<MyUserPrincipal> usersPrincipals;

    @Override
    public String getAuthority() {
        return name;
    }
}
