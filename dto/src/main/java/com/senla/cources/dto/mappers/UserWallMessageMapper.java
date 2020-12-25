package com.senla.cources.dto.mappers;

import com.senla.cources.domain.UserWallMessage;
import com.senla.cources.dto.UserWallMessageDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserWallMessageMapper {

    @FullMapping
    UserWallMessageDto userWallMessageToUserWallMessageDto(UserWallMessage userWallMessage);
    UserWallMessage userWallMessageDtoToUserWallMessage(UserWallMessageDto userWallMessageDto);

    @IterableMapping(qualifiedBy = FullMapping.class)
    @FullMapping
    List<UserWallMessageDto> userWallMessageListToUserWallMessageDtoList(List<UserWallMessage> userWallMessages);
}
