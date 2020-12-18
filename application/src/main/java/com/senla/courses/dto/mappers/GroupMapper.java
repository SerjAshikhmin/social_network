package com.senla.courses.dto.mappers;

import com.senla.courses.domain.Group;
import com.senla.courses.domain.GroupWall;
import com.senla.courses.domain.GroupWallMessage;
import com.senla.courses.domain.User;
import com.senla.courses.domain.security.MyUserPrincipal;
import com.senla.courses.dto.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @FullMapping
    GroupDto groupToGroupDto(Group group);
    Group groupDtoToGroup(GroupDto groupDto);

    @IterableMapping(qualifiedBy = FullMapping.class)
    @FullMapping
    List<GroupDto> groupListToGroupDtoList(List<Group> groups);

    @Mapping(target = "friends", ignore = true)
    @Mapping(target = "userWall", ignore = true)
    @Mapping(target = "outgoingMessages", ignore = true)
    @Mapping(target = "incomingMessages", ignore = true)
    @Mapping(target = "groups", ignore = true)
    //@Mapping(target = "userPrincipal", ignore = true)
    UserDto userToUserDto(User user);

    @Mapping(target = "password", ignore = true)
    MyUserPrincipalDto myUserPrincipalToMyUserPrincipalDto(MyUserPrincipal myUserPrincipal);

    @Mapping(target = "group", ignore = true)
    GroupWallDto groupWallToGroupWallDto(GroupWall groupWall);

    @Mapping(target = "groupWall", ignore = true)
    GroupWallMessageDto groupWallMessageToGroupWallMessageDto(GroupWallMessage groupWallMessage);
}
