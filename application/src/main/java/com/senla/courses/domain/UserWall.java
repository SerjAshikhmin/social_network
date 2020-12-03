package com.senla.courses.domain;

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
public class UserWall extends Wall {

    @OneToOne(mappedBy = "userWall")
    private User user;

    @OneToMany(mappedBy = "userWall", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserWallMessage> messages;
}
