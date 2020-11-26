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
@Table(name = "user_wall")
public class UserWall {

    @Id
    private int id;

    @OneToOne(mappedBy = "userWall")
    private User user;

    @OneToMany(mappedBy = "userWall", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserWallMessage> messages;
}
