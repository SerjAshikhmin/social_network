package com.senla.courses.dto.mappers;

import com.senla.courses.domain.GroupWallMessage;
import com.senla.courses.dto.GroupWallMessageDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = GroupMapper.class)
public interface GroupWallMessageMapper {

    @FullMapping
    GroupWallMessageDto groupWallMessageToGroupWallMessageDto(GroupWallMessage groupWallMessage);
    GroupWallMessage groupWallMessageDtoToGroupWallMessage(GroupWallMessageDto groupWallMessageDto);

    @IterableMapping(qualifiedBy = FullMapping.class)
    @FullMapping
    List<GroupWallMessageDto> groupWallMessageListToGroupWallMessageDtoList(List<GroupWallMessage> groupWallMessages);
}
