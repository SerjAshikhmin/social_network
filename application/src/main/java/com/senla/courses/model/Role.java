package com.senla.courses.model;

//import org.springframework.security.core.GrantedAuthority;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role { //implements GrantedAuthority {

    @Id
    private int id;
    @NonNull
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    /*@Override
    public String getAuthority() {
        return name;
    }*/
}
