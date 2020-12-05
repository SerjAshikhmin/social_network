package com.senla.courses.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@MappedSuperclass
public abstract class Message {

    @Id
    @GeneratedValue
    @Column(name = "id", length = 10)
    private Integer id;
    @NonNull
    private String content;
    @NonNull
    private LocalDateTime sendDate;
}
