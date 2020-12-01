package com.senla.courses.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "group_wall_message")
public class GroupWallMessage extends Message {

    @ManyToOne
    @JoinColumn(name = "group_wall_id")
    private GroupWall groupWall;
}
