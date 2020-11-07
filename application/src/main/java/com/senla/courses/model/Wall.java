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
@Table(name = "wall")
public class Wall {

    @Id
    private int id;
    @OneToOne(mappedBy = "wall")
    private User user;
    @OneToMany(mappedBy = "wall", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PublicMessage> messages;
}
