package com.senla.courses.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
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
    private String title;

    @ManyToMany(mappedBy = "groups", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<User> users;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "groupWall_id", referencedColumnName = "id")
    private GroupWall groupWall;
}
