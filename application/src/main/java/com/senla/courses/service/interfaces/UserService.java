package com.senla.courses.service.interfaces;

import com.senla.courses.domain.User;
import com.senla.courses.domain.security.MyUserPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void registerUser(User user, MyUserPrincipal userPrincipal);
    void updateUserInfo(User user);
    User getUserInfo(int userId);
    List<User> searchUsers(String country, String city, String partOfName, Integer lessThanAgeInYears, Integer moreThanAgeInYears);
    void addToFriends(int userId);
    void removeFromFriends(int userId);
}
