package com.senla.courses.dto.mappers;

import com.senla.courses.domain.security.MyUserPrincipal;
import com.senla.courses.dto.MyUserPrincipalDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyUserPrincipalMapper {

    MyUserPrincipalDto myUserPrincipalToMyUserPrincipalDto(MyUserPrincipal userPrincipal);
    MyUserPrincipal myUserPrincipalDtoToMyUserPrincipal(MyUserPrincipalDto userPrincipalDto);
}
