package com.senla.courses.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "group_wall_message")
public class GroupWallMessage {

    @Id
    private int id;
    @NonNull
    private String content;
    @NonNull
    private LocalDateTime sendDate;

    @ManyToOne
    @JoinColumn(name = "group_wall_id")
    private GroupWall groupWall;
}
