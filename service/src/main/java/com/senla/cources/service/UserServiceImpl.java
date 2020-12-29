package com.senla.cources.service;

import com.senla.cources.domain.User;
import com.senla.cources.domain.UserWall;
import com.senla.cources.domain.enums.Gender;
import com.senla.cources.domain.security.MyUserPrincipal;
import com.senla.cources.domain.security.Role;
import com.senla.cources.dto.UserDto;
import com.senla.cources.dto.mappers.UserMapper;
import com.senla.cources.exceptions.userexceptions.*;
import com.senla.cources.repository.RoleRepository;
import com.senla.cources.repository.UserPrincipalRepository;
import com.senla.cources.repository.UserRepository;
import com.senla.cources.service.interfaces.UserService;
import com.senla.cources.service.security.PermissionService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;


@Slf4j
@Setter
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
    private PermissionService permissionService;

    @Override
    @Transactional
    public void registerUser(UserDto userDto) {
        log.debug(String.format("Call method registerUser for user %s %s", userDto.getFirstName(), userDto.getLastName()));
        User user = userMapper.userDtoToUser(userDto);
        MyUserPrincipal userPrincipal = new MyUserPrincipal(null, userDto.getUserName(), userDto.getPassword(), null, null);
        if (user.getFriends() == null) {
            user.setFriends(new ArrayList<>());
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
            throw new RegisterUserException(e);
        }
    }

    @Override
    @Transactional
    public void updateUserInfo(UserDto userDto) {
        log.debug(String.format("Call method updateUserInfo for user %s %s", userDto.getFirstName(), userDto.getLastName()));
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
            throw new UpdateUserException(e);
        }
    }

    @Override
    public UserDto getUserInfo(int userId) {
        log.debug(String.format("Call method getUserInfo with id %d", userId));
        User user;
        try {
            user = userRepository.findById(userId).get();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UserNotFoundException(e);
        }
        return userMapper.userToUserDto(user);
    }

    @Override
    @Transactional
    public void removeUser(int userId) {
        log.debug(String.format("Call method removeUser with id %d", userId));
        User user;
        try {
            user = userRepository.findById(userId).get();
            if (permissionService.checkPermissionForUserWall(user)) {
                user.setAdminInGroups(Collections.EMPTY_SET);
                user.setGroups(Collections.EMPTY_SET);
                user.setIncomingMessages(Collections.EMPTY_LIST);
                user.setOutgoingMessages(Collections.EMPTY_LIST);
                userRepository.save(user);
                userRepository.delete(user);
                log.info(String.format("User %s successfully removed", user.getUserPrincipal().getUsername()));
            } else {
                String message = String.format("User %s doesn't have permission to remove current user", user.getUserPrincipal().getUsername());
                log.warn(message);
                throw new RuntimeException(message);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RemoveUserException(e);
        }
    }

    @Override
    @Transactional
    public void addToFriends(int userId) {
        log.debug(String.format("Call method addToFriends with id %d", userId));
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            User currentUser = userPrincipalRepository.findMyUserPrincipalByUserName(currentUserName).getUser();
            User user = userRepository.findById(userId).get();
            if (currentUser.getFriends().contains(user)) {
                log.info(String.format("User %s already is a friend of user %s", user.getUserPrincipal().getUsername(), currentUserName));
            } else {
                currentUser.getFriends().add(user);
                user.getFriends().add(currentUser);
                userRepository.save(currentUser);
                log.info(String.format("User %s added as a friend to user %s", user.getUserPrincipal().getUsername(), currentUserName));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new AddToFriendsException(e);
        }
    }

    @Override
    @Transactional
    public void removeFromFriends(int userId) {
        log.debug(String.format("Call method removeFromFriends with id %d", userId));
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            User currentUser = userPrincipalRepository.findMyUserPrincipalByUserName(currentUserName).getUser();
            User user = userRepository.findById(userId).get();
            if (!currentUser.getFriends().contains(user)) {
                log.info(String.format("User %s isn't a friend of user %s", user.getUserPrincipal().getUsername(), currentUserName));
            } else {
                currentUser.getFriends().remove(user);
                user.getFriends().remove(currentUser);
                userRepository.save(currentUser);
                log.info(String.format("User %s removed as a friend of user %s", user.getUserPrincipal().getUsername(), currentUserName));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RemoveFromFriendsException(e);
        }
    }

    @Override
    public List<UserDto> searchUsers(String country, String city, String partOfName, Integer lessThanAgeInYears, Integer moreThanAgeInYears) {
        log.debug("Call method searchUsers");
        List<User> result;
        List<User> usersByCountryAndCity;
        usersByCountryAndCity = applyCountryAndCityFilters(country, city);
        result = new ArrayList<>(usersByCountryAndCity);
        applyPartOfNameFilter(partOfName, result, usersByCountryAndCity);
        applyAgeFilters(lessThanAgeInYears, moreThanAgeInYears, result);
        result.sort(Comparator.comparing(User::getFirstName));
        return userMapper.userListToUserDtoList(result);
    }

    private void applyAgeFilters(Integer lessThanAgeInYears, Integer moreThanAgeInYears, List<User> result) {
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
    }

    private void applyPartOfNameFilter(String partOfName, List<User> result, List<User> usersByCountryAndCity) {
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
    }

    private List<User> applyCountryAndCityFilters(String country, String city) {
        List<User> usersByCountryAndCity = null;
        try {
            if (country != null && city != null) {
                usersByCountryAndCity = userRepository.getUsersByCountryAndCity(country, city);
            } else {
                if (country != null) {
                    usersByCountryAndCity = userRepository.getUsersByCountry(country);
                } else {
                    usersByCountryAndCity = userRepository.findAll();
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UserNotFoundException(e);
        }
        return usersByCountryAndCity;
    }
}
