package com.senla.courses.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @Column(nullable = false, length = 1000)
    private String content;
    @NotNull
    @Column(nullable = false)
    private LocalDateTime sendDate;
}
