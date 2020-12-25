package com.senla.cources.dto.mappers;

import com.senla.cources.domain.PrivateMessage;
import com.senla.cources.dto.PrivateMessageDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = GroupMapper.class)
public interface PrivateMessageMapper {

    @FullMapping
    PrivateMessageDto privateMessageToPrivateMessageDto(PrivateMessage privateMessage);
    PrivateMessage privateMessageDtoToPrivateMessage(PrivateMessageDto privateMessageDto);

    @IterableMapping(qualifiedBy = FullMapping.class)
    @FullMapping
    List<PrivateMessageDto> privateMessageListToPrivateMessageDtoList(List<PrivateMessage> privateMessages);
}
