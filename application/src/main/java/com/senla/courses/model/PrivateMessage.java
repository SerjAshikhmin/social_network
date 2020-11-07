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
@Table(name = "private_message")
public class PrivateMessage {

    @Id
    private int id;
    @NonNull
    private String content;
    @NonNull
    private LocalDateTime sendDate;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "users_id", insertable = false, updatable = false)
    private User receiver;
}
