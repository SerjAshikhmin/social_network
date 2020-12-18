package com.senla.courses.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Setter
@Getter
@MappedSuperclass
public abstract class Wall {

    @Id
    @GeneratedValue
    @Column(name = "id", length = 10)
    private Integer id;
}
