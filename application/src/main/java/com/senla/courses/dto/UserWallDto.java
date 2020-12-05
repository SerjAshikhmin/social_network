package com.senla.courses.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class UserWallDto {

    private int id;
    private UserDto user;
    private List<UserWallMessageDto> messages;
}
