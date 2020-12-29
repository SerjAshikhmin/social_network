package com.senla.cources.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "groupes")
public class Group {

    @Id
    private int id;
    @NotNull
    @Column(nullable = false, length = 45)
    private String title;

    @ManyToMany(mappedBy = "groups", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<User> users;

    @ManyToMany(mappedBy = "adminInGroups", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<User> admins;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "groupWall_id", referencedColumnName = "id")
    private GroupWall groupWall;
}
