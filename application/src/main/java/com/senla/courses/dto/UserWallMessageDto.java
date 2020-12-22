package com.senla.courses.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
public class UserWallMessageDto {

    private Integer id;
    @NotEmpty
    @Size(min = 1, max = 1000)
    private String content;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime sendDate;
    private UserWallDto userWall;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWallMessageDto that = (UserWallMessageDto) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(sendDate, that.sendDate) && Objects.equals(userWall, that.userWall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, sendDate, userWall);
    }
}
