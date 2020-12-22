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
public class PrivateMessageDto {

    private Integer id;
    @NotEmpty
    @Size(min = 1, max = 1000)
    private String content;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime sendDate;
    private boolean isRead;
    private UserDto sender;
    private UserDto receiver;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivateMessageDto that = (PrivateMessageDto) o;
        return isRead == that.isRead && Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(sendDate, that.sendDate) && Objects.equals(sender, that.sender) && Objects.equals(receiver, that.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, sendDate, isRead, sender, receiver);
    }
}
