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
@Table(name = "public_message")
public class PublicMessage {

    @Id
    private int id;
    @NonNull
    private String content;
    @NonNull
    private LocalDateTime sendDate;
    @ManyToOne
    @JoinColumn(name = "wall_id")
    private Wall wall;

}
