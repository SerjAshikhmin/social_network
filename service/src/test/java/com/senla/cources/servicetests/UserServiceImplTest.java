package com.senla.cources.servicetests;

import com.senla.cources.domain.User;
import com.senla.cources.domain.security.MyUserPrincipal;
import com.senla.cources.dto.UserDto;
import com.senla.cources.dto.mappers.MyUserPrincipalMapper;
import com.senla.cources.dto.mappers.MyUserPrincipalMapperImpl;
import com.senla.cources.dto.mappers.UserMapper;
import com.senla.cources.dto.mappers.UserMapperImpl;
import com.senla.cources.exceptions.userexceptions.AddToFriendsException;
import com.senla.cources.exceptions.userexceptions.RemoveFromFriendsException;
import com.senla.cources.exceptions.userexceptions.UpdateUserException;
import com.senla.cources.exceptions.userexceptions.UserNotFoundException;
import com.senla.cources.repository.RoleRepository;
import com.senla.cources.repository.UserPrincipalRepository;
import com.senla.cources.repository.UserRepository;
import com.senla.cources.service.UserServiceImpl;
import com.senla.cources.utils.TestData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserPrincipalRepository userPrincipalRepository;
    @Mock
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private MyUserPrincipalMapper userPrincipalMapper;
    private TestData testData;

    public UserServiceImplTest() {
        this.userMapper = new UserMapperImpl();
        this.userPrincipalMapper = new MyUserPrincipalMapperImpl();
        this.testData = new TestData();
    }

    @BeforeAll
    public static void startGroupWallServiceImplTests() {
        log.info("Starting userServiceImpl tests");
    }

    @BeforeEach
    public void setUpMocks() {
        MockitoAnnotations.openMocks(this);
        userService.setUserMapper(userMapper);
        userService.setUserPrincipalMapper(userPrincipalMapper);
    }

    @Test
    public void registerUserTest() {
        log.info("Starting registerUser test");
        when(roleRepository.findRoleByName("user")).thenReturn(testData.getDefaultTestRole());

        userService.registerUser(userMapper.userToUserDto(testData.getTestUserList().get(0)));

        verify(roleRepository, times(1)).findRoleByName("user");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void updateUserInfoTest() {
        log.info("Starting updateUserInfo test");
        User testUser = testData.getTestUserList().get(0);
        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));
        testUser.setCity("Orel");

        userService.updateUserInfo(userMapper.userToUserDto(testUser));

        verify(userRepository, times(1)).findById(1);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void updateNotExistingUserInfoTest() {
        log.info("Starting updateNotExistingUserInfo test");
        User testUser = testData.getTestUserList().get(0);
        when(userRepository.findById(1)).thenThrow(NoSuchElementException.class);

        assertThrows(UpdateUserException.class, () -> userService.updateUserInfo(userMapper.userToUserDto(testUser)));
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    public void getUserInfoTest() {
        log.info("Starting getUserInfo test");
        User testUser = testData.getTestUserList().get(0);
        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));
        UserDto expectedResult = userMapper.userToUserDto(testUser);

        UserDto actualResult = userService.getUserInfo(1);

        assertEquals(expectedResult, actualResult);
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    public void getNotExistingUserInfoTest() {
        log.info("Starting getNotExistingUserInfo test");
        when(userRepository.findById(5)).thenThrow(NoSuchElementException.class);

        assertThrows(UserNotFoundException.class, () -> userService.getUserInfo(5));
        verify(userRepository, times(1)).findById(5);
    }

    @Test
    public void addToFriendsTest() {
        log.info("Starting addToFriends test");
        User testUser = testData.getTestUserList().get(1);
        MyUserPrincipal testPrincipal = testData.getTestUserPrincipals().get(0);
        when(userRepository.findById(2)).thenReturn(Optional.of(testUser));
        when(userPrincipalRepository.findMyUserPrincipalByUserName(anyString())).thenReturn(testPrincipal);
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("testKey", testPrincipal.getUsername(), testPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        userService.addToFriends(2);

        verify(userRepository, times(1)).findById(2);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void addToFriendsUserAlreadyInFriendsTest() {
        log.info("Starting addToFriendsUserAlreadyInFriends test");
        User testUser = testData.getTestUserList().get(2);
        MyUserPrincipal testPrincipal = testData.getTestUserPrincipals().get(0);
        when(userRepository.findById(3)).thenReturn(Optional.of(testUser));
        when(userPrincipalRepository.findMyUserPrincipalByUserName(anyString())).thenReturn(testPrincipal);
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("testKey", testPrincipal.getUsername(), testPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        userService.addToFriends(3);

        verify(userRepository, times(1)).findById(3);
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void addToFriendsNotExistingUserTest() {
        log.info("Starting addToFriendsNotExistingUser test");
        MyUserPrincipal testPrincipal = testData.getTestUserPrincipals().get(0);
        when(userRepository.findById(5)).thenThrow(NoSuchElementException.class);
        when(userPrincipalRepository.findMyUserPrincipalByUserName(anyString())).thenReturn(testPrincipal);
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("testKey", testPrincipal.getUsername(), testPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        assertThrows(AddToFriendsException.class, () -> userService.addToFriends(5));
        verify(userRepository, times(1)).findById(5);
    }

    @Test
    public void removeFromFriendsTest() {
        log.info("Starting removeFromFriends test");
        User testUser = testData.getTestUserList().get(2);
        MyUserPrincipal testPrincipal = testData.getTestUserPrincipals().get(0);
        when(userRepository.findById(3)).thenReturn(Optional.of(testUser));
        when(userPrincipalRepository.findMyUserPrincipalByUserName(anyString())).thenReturn(testPrincipal);
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("testKey", testPrincipal.getUsername(), testPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        userService.removeFromFriends(3);

        verify(userRepository, times(1)).findById(3);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void removeFromFriendsUserNotInFriendsTest() {
        log.info("Starting removeFromFriendsUserNotInFriends test");
        User testUser = testData.getTestUserList().get(1);
        MyUserPrincipal testPrincipal = testData.getTestUserPrincipals().get(0);
        when(userRepository.findById(2)).thenReturn(Optional.of(testUser));
        when(userPrincipalRepository.findMyUserPrincipalByUserName(anyString())).thenReturn(testPrincipal);
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("testKey", testPrincipal.getUsername(), testPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        userService.removeFromFriends(2);

        verify(userRepository, times(1)).findById(2);
        verify(userRepository, times(0)).save(any(User.class));
    }

    @Test
    public void removeFromFriendsNotExistingUserTest() {
        log.info("Starting removeFromFriendsNotExistingUser test");
        MyUserPrincipal testPrincipal = testData.getTestUserPrincipals().get(0);
        when(userRepository.findById(5)).thenThrow(NoSuchElementException.class);
        when(userPrincipalRepository.findMyUserPrincipalByUserName(anyString())).thenReturn(testPrincipal);
        AnonymousAuthenticationToken token = new AnonymousAuthenticationToken("testKey", testPrincipal.getUsername(), testPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        assertThrows(RemoveFromFriendsException.class, () -> userService.removeFromFriends(5));
        verify(userRepository, times(1)).findById(5);
    }

    @Test
    public void searchUsersWithEmptyFiltersTest() {
        log.info("Starting searchUsersWithEmptyFilters test");
        List<User> testUsers = testData.getTestUserList();
        testUsers.sort(Comparator.comparing(User::getFirstName));
        when(userRepository.findAll()).thenReturn(testUsers);
        List<UserDto> expectedResult = userMapper.userListToUserDtoList(testUsers);

        List<UserDto> actualResult = userService.searchUsers(null, null, null, null, null);

        assertEquals(expectedResult, actualResult);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void searchUsersWithCountryAndCityTest() {
        log.info("Starting searchUsersWithCountryAndCity test");
        List<User> testUsers = testData.getTestUsersWithCountryAndCity();
        testUsers.sort(Comparator.comparing(User::getFirstName));
        when(userRepository.getUsersByCountryAndCity("Russia", "St. Petersburg")).thenReturn(testUsers);
        List<UserDto> expectedResult = userMapper.userListToUserDtoList(testUsers);

        List<UserDto> actualResult = userService.searchUsers("Russia", "St. Petersburg", null, null, null);

        assertEquals(expectedResult, actualResult);
        verify(userRepository, times(1)).getUsersByCountryAndCity(anyString(), anyString());
    }

    @Test
    public void searchUsersWithPartOfNameTest() {
        log.info("Starting searchUsersWithPartOfName test");
        List<User> testUsers = testData.getTestUsersWithPartOfName();
        testUsers.sort(Comparator.comparing(User::getFirstName));
        when(userRepository.findAll()).thenReturn(testUsers);
        List<UserDto> expectedResult = userMapper.userListToUserDtoList(testUsers);

        List<UserDto> actualResult = userService.searchUsers(null, null, "Micha", null, null);

        assertEquals(expectedResult, actualResult);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void searchUsersWithAgeTest() {
        log.info("Starting searchUsersWithAge test");
        List<User> testUsers = testData.getTestUsersWithAge();
        testUsers.sort(Comparator.comparing(User::getFirstName));
        when(userRepository.findAll()).thenReturn(testUsers);
        List<UserDto> expectedResult = userMapper.userListToUserDtoList(testUsers);

        List<UserDto> actualResult = userService.searchUsers(null, null, null, 28, 24);

        assertEquals(expectedResult, actualResult);
        verify(userRepository, times(1)).findAll();
    }
}
