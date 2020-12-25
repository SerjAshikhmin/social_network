package com.senla.cources.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName) && Objects.equals(gender, userDto.gender) && Objects.equals(birthDate, userDto.birthDate) && Objects.equals(country, userDto.country) && Objects.equals(city, userDto.city) && Objects.equals(personalInfo, userDto.personalInfo) && Objects.equals(friends, userDto.friends) && Objects.equals(userWall, userDto.userWall) && Objects.equals(outgoingMessages, userDto.outgoingMessages) && Objects.equals(incomingMessages, userDto.incomingMessages) && Objects.equals(groups, userDto.groups) && Objects.equals(userName, userDto.userName) && Objects.equals(password, userDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, birthDate, country, city, personalInfo, friends, userWall, outgoingMessages, incomingMessages, groups, userName, password);
    }
}
