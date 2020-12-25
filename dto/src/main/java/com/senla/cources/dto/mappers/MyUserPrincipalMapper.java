package com.senla.cources.dto.mappers;

import com.senla.cources.domain.security.MyUserPrincipal;
import com.senla.cources.dto.MyUserPrincipalDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyUserPrincipalMapper {

    MyUserPrincipalDto myUserPrincipalToMyUserPrincipalDto(MyUserPrincipal userPrincipal);
    MyUserPrincipal myUserPrincipalDtoToMyUserPrincipal(MyUserPrincipalDto userPrincipalDto);
}
