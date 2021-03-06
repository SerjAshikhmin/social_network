package com.senla.cources.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MyUserPrincipalDto {

    private Integer id;
    private String userName;
    private String password;
}
