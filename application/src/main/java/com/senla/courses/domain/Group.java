package com.senla.courses.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "groupes")
public class Group {

    @Id
    private int id;
    private String title;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users;

    @OneToOne
    @JoinColumn(name = "groupWall_id", referencedColumnName = "id")
    private GroupWall groupWall;
}
