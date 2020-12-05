package com.senla.courses.dto.mappers;

import com.senla.courses.domain.GroupWallMessage;
import com.senla.courses.domain.UserWallMessage;
import com.senla.courses.dto.GroupWallMessageDto;
import com.senla.courses.dto.UserWallMessageDto;
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
