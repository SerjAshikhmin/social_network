package com.senla.cources.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user_wall_message")
public class UserWallMessage extends Message {

    @ManyToOne
    @JoinColumn(name = "user_wall_id")
    private UserWall userWall;

    public UserWallMessage(int id, String content, LocalDateTime sendDate, UserWall userWall) {
        super(id, content, sendDate);
        this.userWall = userWall;
    }
}
