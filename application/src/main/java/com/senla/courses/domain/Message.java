package com.senla.courses.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
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
