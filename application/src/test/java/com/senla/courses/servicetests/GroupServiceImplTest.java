package com.senla.courses.servicetests;

import com.senla.courses.domain.Group;
import com.senla.courses.domain.User;
import com.senla.courses.dto.GroupDto;
import com.senla.courses.dto.mappers.GroupMapper;
import com.senla.courses.dto.mappers.GroupMapperImpl;
import com.senla.courses.exceptions.groupexceptions.GroupNotFoundException;
import com.senla.courses.exceptions.groupexceptions.RemoveGroupException;
import com.senla.courses.exceptions.groupexceptions.RemoveUserFromGroupException;
import com.senla.courses.repository.GroupRepository;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.GroupServiceImpl;
import com.senla.courses.utils.TestData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class GroupServiceImplTest {

    @InjectMocks
    private GroupServiceImpl groupService;
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private UserRepository userRepository;
    private final GroupMapper groupMapper;
    private final TestData testData;

    public GroupServiceImplTest() {
        this.groupMapper = new GroupMapperImpl();
        this.testData = new TestData();
    }

    @BeforeAll
    public static void startGroupServiceImplTests() {
        log.info("Starting groupServiceImpl tests");
    }

    @BeforeEach
    public void setUpMocks() {
        MockitoAnnotations.openMocks(this);
        groupService.setGroupMapper(groupMapper);
    }

    @Test
    public void getAllGroupsTest() {
        log.info("Starting getAllGroups test");
        List<Group> testGroupList = testData.getTestGroupList();
        when(groupRepository.findAll()).thenReturn(testGroupList);
        List<GroupDto> expectedResult = groupMapper.groupListToGroupDtoList(testGroupList);

        List<GroupDto> actualResult = groupService.getAllGroups();

        assertEquals(actualResult, expectedResult);
        verify(groupRepository, times(1)).findAll();
    }

    @Test
    public void addGroupTest() {
        log.info("Starting addGroup test");

        groupService.addGroup(new GroupDto(1, "Kinomania", null, null));

        verify(groupRepository, times(1)).save(any(Group.class));
    }

    @Test
    public void removeGroupTest() {
        log.info("Starting removeGroup test");
        Group testGroup = testData.getTestGroupList().get(0);
        when(groupRepository.findById(anyInt())).thenReturn(Optional.of(testGroup));

        groupService.removeGroup(1);

        verify(groupRepository, times(1)).findById(anyInt());
        verify(groupRepository, times(1)).delete(any(Group.class));
    }

    @Test
    public void removeNotExistingGroupTest() {
        log.info("Starting removeNotExistingGroup test");
        when(groupRepository.findById(5)).thenThrow(new NoSuchElementException());

        assertThrows(RemoveGroupException.class, () -> groupService.removeGroup(5));
        verify(groupRepository, times(1)).findById(anyInt());
    }

    @Test
    public void findGroupByIdTest() {
        log.info("Starting findGroupById test");
        Group testGroup = testData.getTestGroupList().get(0);
        when(groupRepository.findById(anyInt())).thenReturn(Optional.of(testGroup));
        GroupDto expectedResult = groupMapper.groupToGroupDto(testGroup);

        GroupDto actualResult = groupService.findGroupById(1);

        assertEquals(actualResult, expectedResult);
        verify(groupRepository, times(1)).findById(anyInt());
    }

    @Test
    public void findNotExistingGroupByIdTest() {
        log.info("Starting findNotExistingGroupById test");
        when(groupRepository.findById(5)).thenThrow(new NoSuchElementException());

        assertThrows(GroupNotFoundException.class, () -> groupService.findGroupById(5));
        verify(groupRepository, times(1)).findById(anyInt());
    }

    @Test
    public void addUserToGroupTest() {
        log.info("Starting addUserToGroup test");
        Group testGroup = testData.getTestGroupList().get(0);
        User testUser = testData.getTestUserList().get(0);
        when(groupRepository.findById(anyInt())).thenReturn(Optional.of(testGroup));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));

        groupService.addUserToGroup(1, 1);

        verify(groupRepository, times(1)).save(any(Group.class));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void removeUserFromGroupTest() {
        log.info("Starting removeUserFromGroup test");
        Group testGroup = testData.getTestGroupList().get(0);
        User testUser = testData.getTestUserList().get(0);
        when(groupRepository.findById(anyInt())).thenReturn(Optional.of(testGroup));
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));

        groupService.removeUserFromGroup(1, 1);

        verify(groupRepository, times(1)).findById(anyInt());
        verify(userRepository, times(1)).findById(anyInt());
        verify(groupRepository, times(1)).save(any(Group.class));
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void removeNotExistingUserFromGroupTest() {
        log.info("Starting removeNotExistingUserFromGroup test");
        when(userRepository.findById(5)).thenThrow(new NoSuchElementException());

        assertThrows(RemoveUserFromGroupException.class, () -> groupService.removeUserFromGroup(5, 1));
        verify(userRepository, times(1)).findById(anyInt());
    }

    @Test
    public void removeUserFromNotExistingGroupTest() {
        log.info("Starting removeUserFromNotExistingGroup test");
        User testUser = testData.getTestUserList().get(0);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(testUser));
        when(groupRepository.findById(5)).thenThrow(new NoSuchElementException());

        assertThrows(RemoveUserFromGroupException.class, () -> groupService.removeUserFromGroup(1, 5));
        verify(groupRepository, times(1)).findById(anyInt());
        verify(userRepository, times(1)).findById(anyInt());
    }
}