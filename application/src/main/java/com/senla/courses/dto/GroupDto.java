package com.senla.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GroupDto {

    private int id;
    @NotEmpty
    @Size(min = 1, max = 45)
    private String title;
    private Set<UserDto> users;
    private GroupWallDto groupWall;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDto groupDto = (GroupDto) o;
        return id == groupDto.id && Objects.equals(title, groupDto.title) && Objects.equals(users, groupDto.users) && Objects.equals(groupWall, groupDto.groupWall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, users, groupWall);
    }
}
