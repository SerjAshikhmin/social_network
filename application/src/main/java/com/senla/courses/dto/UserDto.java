package com.senla.courses.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String country;
    private String city;
    private String personalInfo;
    private List<UserDto> friends;
    private List<UserDto> inFriends;
    private UserWallDto userWall;
    private List<PrivateMessageDto> outgoingMessages;
    private List<PrivateMessageDto> incomingMessages;
    private Set<GroupDto> groups;
    private String userName;
    private String password;
}
