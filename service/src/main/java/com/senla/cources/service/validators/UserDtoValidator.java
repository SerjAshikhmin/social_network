package com.senla.cources.service.validators;

import com.senla.cources.dto.UserDto;
import com.senla.cources.service.security.UserPrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserDtoValidator implements Validator {

    @Autowired
    private UserPrincipalService userPrincipalService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto) target;
        if (userPrincipalService.isUserAlreadyExists(userDto.getUserName())) {
            errors.rejectValue("userName", "userName.alreadyExists", String.format("User with userName %s already exists", userDto.getUserName()));
        }
    }
}
