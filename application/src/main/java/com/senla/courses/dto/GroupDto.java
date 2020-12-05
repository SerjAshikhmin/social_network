package com.senla.courses.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class GroupDto {

    private int id;
    private String title;
    private Set<UserDto> users;
    private GroupWallDto groupWall;
}
