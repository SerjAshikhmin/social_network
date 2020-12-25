package com.senla.cources.dto.mappers;

import com.senla.cources.domain.GroupWallMessage;
import com.senla.cources.dto.GroupWallMessageDto;
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
