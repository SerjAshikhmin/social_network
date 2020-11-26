package com.senla.courses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "groups")
public class Group {

    @Id
    private int id;
    private String title;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users;

    @OneToOne
    @PrimaryKeyJoinColumn
    private GroupWall groupWall;
}
