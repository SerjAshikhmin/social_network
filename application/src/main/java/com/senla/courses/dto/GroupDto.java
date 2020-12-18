package com.senla.courses.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
public class GroupDto {

    private int id;
    @NotEmpty
    @Size(min = 1, max = 45)
    private String title;
    private Set<UserDto> users;
    private GroupWallDto groupWall;
}
