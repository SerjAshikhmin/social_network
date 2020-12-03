package com.senla.courses.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "group_wall_message")
public class GroupWallMessage extends Message {

    @ManyToOne
    @JoinColumn(name = "group_wall_id")
    private GroupWall groupWall;

    public GroupWallMessage(int id, String content, LocalDateTime sendDate, GroupWall groupWall) {
        super(id, content, sendDate);
        this.groupWall = groupWall;
    }
}
