package com.senla.courses.service.interfaces;

import com.senla.courses.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void registerUser(UserDto user);
    void updateUserInfo(UserDto user);
    UserDto getUserInfo(int userId);
    List<UserDto> searchUsers(String country, String city, String partOfName, Integer lessThanAgeInYears, Integer moreThanAgeInYears);
    void addToFriends(int userId);
    void removeFromFriends(int userId);
}
