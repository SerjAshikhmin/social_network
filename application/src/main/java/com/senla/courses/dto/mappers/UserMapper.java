package com.senla.courses.dto.mappers;

import com.senla.courses.domain.Group;
import com.senla.courses.domain.User;
import com.senla.courses.domain.UserWall;
import com.senla.courses.domain.UserWallMessage;
import com.senla.courses.domain.security.MyUserPrincipal;
import com.senla.courses.dto.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @FullMapping
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);

    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "inFriends", ignore = true)
    @Mapping(target = "userWall", ignore = true)
    @Mapping(target = "outgoingMessages", ignore = true)
    @Mapping(target = "incomingMessages", ignore = true)
    @Mapping(target = "groups", ignore = true)
    UserDto userToUserDto1(User user);

    @IterableMapping(qualifiedBy = FullMapping.class)
    @FullMapping
    List<UserDto> userListToUserDtoList(List<User> users);

    @Mapping(target = "user", ignore = true)
    UserWallDto userWallToUserWallDto(UserWall userWall);

    @Mapping(target = "groupWall", ignore = true)
    @Mapping(target = "users", ignore = true)
    GroupDto groupToGroupDto(Group group);

    @Mapping(target = "userWall", ignore = true)
    UserWallMessageDto userWallMessageToUserWallMessageDto(UserWallMessage userWallMessage);

    @Mapping(target = "password", ignore = true)
    MyUserPrincipalDto myUserPrincipalToMyUserPrincipalDto(MyUserPrincipal myUserPrincipal);
}
