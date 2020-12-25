package com.senla.cources.dto.mappers;

import com.senla.cources.domain.Group;
import com.senla.cources.domain.GroupWall;
import com.senla.cources.domain.GroupWallMessage;
import com.senla.cources.domain.User;
import com.senla.cources.domain.security.MyUserPrincipal;
import com.senla.cources.dto.*;
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
