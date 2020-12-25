package com.senla.cources.domain;

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
public class GroupWall extends Wall {

    @OneToOne(mappedBy = "groupWall")
    private Group group;

    @OneToMany(mappedBy = "groupWall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GroupWallMessage> messages;
}
