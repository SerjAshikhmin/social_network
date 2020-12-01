package com.senla.courses.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user_wall_message")
public class UserWallMessage extends Message {

    @ManyToOne
    @JoinColumn(name = "user_wall_id")
    private UserWall userWall;
}
