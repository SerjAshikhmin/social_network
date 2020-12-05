package com.senla.courses.service;

import com.senla.courses.domain.User;
import com.senla.courses.domain.UserWall;
import com.senla.courses.domain.enums.Gender;
import com.senla.courses.domain.security.MyUserPrincipal;
import com.senla.courses.domain.security.Role;
import com.senla.courses.dto.UserDto;
import com.senla.courses.dto.mappers.MyUserPrincipalMapper;
import com.senla.courses.dto.mappers.UserMapper;
import com.senla.courses.repository.RoleRepository;
import com.senla.courses.repository.UserPrincipalRepository;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPrincipalRepository userPrincipalRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MyUserPrincipalMapper userPrincipalMapper;

    @Override
    @Transactional
    public void registerUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        MyUserPrincipal userPrincipal = new MyUserPrincipal(null, userDto.getUserName(), userDto.getPassword(), null, null);
        if (user.getFriends() == null) {
            user.setFriends(new ArrayList<>());
        }
        if (user.getInFriends() == null) {
            user.setInFriends(new ArrayList<>());
        }
        if (user.getUserWall() == null) {
            user.setUserWall(new UserWall());
        }
        if (user.getIncomingMessages() == null) {
            user.setIncomingMessages(new ArrayList<>());
        }
        if (user.getOutgoingMessages() == null) {
            user.setOutgoingMessages(new ArrayList<>());
        }
        if (userPrincipal.getRoles() == null) {
            userPrincipal.setRoles(new HashSet<>());
            Role defaultRole = roleRepository.findRoleByName("user");
            userPrincipal.getRoles().add(defaultRole);
        }
        if (user.getUserPrincipal() == null) {
            user.setUserPrincipal(userPrincipal);
        }
        if (user.getGroups() == null) {
            user.setGroups(new HashSet<>());
        }
        try {
            userRepository.save(user);
            log.info(String.format("User %s successfully registered", userPrincipal.getUsername()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void updateUserInfo(UserDto userDto) {
        try {
            User user = userRepository.findById(userDto.getId()).get();
            if (userDto.getPassword() != null) {
                user.setUserPrincipal(user.getUserPrincipal());
                user.getUserPrincipal().setPassword(userDto.getPassword());
            }
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setGender(Gender.valueOf(userDto.getGender()));
            if (userDto.getBirthDate() != null) {
                user.setBirthDate(userDto.getBirthDate());
            } else {
                user.setBirthDate(null);
            }
            if (userDto.getCountry() != null) {
                user.setCountry(userDto.getCountry());
            } else {
                user.setCountry(null);
            }
            if (userDto.getCity() != null) {
                user.setCity(userDto.getCity());
            } else {
                user.setCity(null);
            }
            if (userDto.getPersonalInfo() != null) {
                user.setPersonalInfo(userDto.getPersonalInfo());
            } else {
                user.setPersonalInfo(null);
            }
            userRepository.save(user);
            log.info(String.format("User %s successfully updated", user.getUserPrincipal().getUsername()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public UserDto getUserInfo(int userId) {
        User user = null;
        try {
            user = userRepository.findById(userId).get();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<UserDto> searchUsers(String country, String city, String partOfName, Integer lessThanAgeInYears, Integer moreThanAgeInYears) {
        List<User> result;
        List<User> usersByCountryAndCity;
        if (country != null && city != null) {
            usersByCountryAndCity = userRepository.getUsersByCountryAndCity(country, city);
        } else {
            if (country != null) {
                usersByCountryAndCity = userRepository.getUsersByCountry(country);
            } else {
                usersByCountryAndCity = userRepository.findAll();
            }
        }
        result = new ArrayList<>(usersByCountryAndCity);
        if (partOfName != null) {
            partOfName = partOfName.toLowerCase();
            for (User user : usersByCountryAndCity) {
                if (!(user.getFirstName().toLowerCase().contains(partOfName) || user.getLastName().toLowerCase().contains(partOfName)
                    || (user.getFirstName().toLowerCase() + " " + user.getLastName().toLowerCase()).contains(partOfName)
                    || (user.getLastName().toLowerCase() + " " + user.getFirstName().toLowerCase()).contains(partOfName))) {
                    result.remove(user);
                }
            }
        }
        if (lessThanAgeInYears != null) {
            List<User> tempUserList = new ArrayList<>(result);
            for (User user : tempUserList) {
                int usersAgeInYears = LocalDateTime.now().minusYears(user.getBirthDate().getYear()).getYear();
                if (usersAgeInYears > lessThanAgeInYears) {
                    result.remove(user);
                }
            }
        }
        if (moreThanAgeInYears != null) {
            List<User> tempUserList = new ArrayList<>(result);
            for (User user : tempUserList) {
                int usersAgeInYears = LocalDateTime.now().minusYears(user.getBirthDate().getYear()).getYear();
                if (usersAgeInYears < moreThanAgeInYears) {
                    result.remove(user);
                }
            }
        }
        result.sort(Comparator.comparing(User::getFirstName));
        return userMapper.userListToUserDtoList(result);
    }

    @Override
    @Transactional
    public void addToFriends(int userId) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            User currentUser = userPrincipalRepository.findMyUserPrincipalByUserName(currentUserName).getUser();
            User user = userRepository.findById(userId).get();
            if (currentUser.getFriends().contains(user)) {
                log.info(String.format("User %s already is a friend of user %s", user.getUserPrincipal().getUsername(), currentUserName));
            } else {
                currentUser.getFriends().add(user);
                currentUser.getInFriends().add(user);
                user.getFriends().add(currentUser);
                user.getInFriends().add(currentUser);
                userRepository.save(currentUser);
                log.info(String.format("User %s added as a friend to user %s", user.getUserPrincipal().getUsername(), currentUserName));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void removeFromFriends(int userId) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            User currentUser = userPrincipalRepository.findMyUserPrincipalByUserName(currentUserName).getUser();
            User user = userRepository.findById(userId).get();
            if (!currentUser.getFriends().contains(user)) {
                log.info(String.format("User %s isn't a friend of user %s", user.getUserPrincipal().getUsername(), currentUserName));
            } else {
                currentUser.getFriends().remove(user);
                currentUser.getInFriends().remove(user);
                user.getFriends().remove(currentUser);
                user.getInFriends().remove(currentUser);
                userRepository.save(currentUser);
                log.info(String.format("User %s removed as a friend of user %s", user.getUserPrincipal().getUsername(), currentUserName));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
