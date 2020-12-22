package com.senla.courses.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
public class UserWallDto {

    private int id;
    private UserDto user;
    private List<UserWallMessageDto> messages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWallDto that = (UserWallDto) o;
        return id == that.id && Objects.equals(user, that.user) && Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, messages);
    }
}
