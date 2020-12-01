package com.senla.courses.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public abstract class Message {

    @Id
    private int id;
    @NonNull
    private String content;
    @NonNull
    private LocalDateTime sendDate;
}
