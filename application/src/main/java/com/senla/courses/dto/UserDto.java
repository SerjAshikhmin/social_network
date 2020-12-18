package com.senla.courses.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    private Integer id;
    @NotEmpty
    @Size(min = 2, max = 20)
    @Pattern(regexp = "[a-zA-Z]+")
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 20)
    @Pattern(regexp = "[a-zA-Z]+")
    private String lastName;
    private String gender;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @Size(max = 45)
    private String country;
    @Size(max = 20)
    private String city;
    @Size(max = 1000)
    private String personalInfo;
    private List<UserDto> friends;
    private UserWallDto userWall;
    private List<PrivateMessageDto> outgoingMessages;
    private List<PrivateMessageDto> incomingMessages;
    private Set<GroupDto> groups;
    @NotEmpty
    @Size(min = 6, max = 45)
    private String userName;
    @NotEmpty
    @Size(min = 5, max = 45)
    private String password;
}
