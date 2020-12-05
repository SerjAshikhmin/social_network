package com.senla.courses.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class GroupWallDto {

    private int id;
    private GroupDto group;
    private List<GroupWallMessageDto> messages;
}
