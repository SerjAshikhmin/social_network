package com.senla.courses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "group_wall")
public class GroupWall {

    @Id
    private int id;

    @OneToOne(mappedBy = "groupWall")
    private Group group;

    @OneToMany(mappedBy = "groupWall", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<GroupWallMessage> messages;
}
