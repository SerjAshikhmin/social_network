package com.senla.cources.service;

import com.senla.cources.domain.Group;
import com.senla.cources.domain.GroupWall;
import com.senla.cources.domain.User;
import com.senla.cources.dto.GroupDto;
import com.senla.cources.dto.mappers.GroupMapper;
import com.senla.cources.exceptions.groupexceptions.*;
import com.senla.cources.repository.GroupRepository;
import com.senla.cources.repository.UserPrincipalRepository;
import com.senla.cources.repository.UserRepository;
import com.senla.cources.service.interfaces.GroupService;
import com.senla.cources.service.security.PermissionService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;


@Slf4j
@Setter
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private UserPrincipalRepository userPrincipalRepository;
    @Autowired
    private PermissionService permissionService;

    @Override
    @Transactional
    public void addGroup(GroupDto groupDto) {
        log.debug(String.format("Call method addGroup for group %s", groupDto.getTitle()));
        try {
            Group group = groupMapper.groupDtoToGroup(groupDto);
            group.setGroupWall(new GroupWall());
            group.setUsers(new HashSet<>());
            groupRepository.save(group);
            log.info(String.format("Group %s successfully created", group.getTitle()));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new AddGroupException(e);
        }
    }

    @Override
    @Transactional
    public void removeGroup(int id) {
        log.debug(String.format("Call method removeGroup with id %d", id));
        try {
            Group group = groupRepository.findById(id).get();
            if (permissionService.checkPermissionForGroup(group)) {
                group.setUsers(Collections.EMPTY_SET);
                group.setAdmins(Collections.EMPTY_SET);
                groupRepository.save(group);
                groupRepository.delete(group);
                log.info(String.format("Group %s successfully removed", group.getTitle()));
            } else {
                String message = String.format("Current user doesn't have permission to remove group %s", group.getTitle());
                log.warn(message);
                throw new RuntimeException(message);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RemoveGroupException(e);
        }
    }

    @Override
    public List<GroupDto> getAllGroups() {
        log.debug("Call method getAllGroups");
        List<Group> result;
        try {
            result = groupRepository.findAll();
            if (result == null || result.isEmpty()) {
                throw new RuntimeException("Groups not found");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GroupNotFoundException(e);
        }
        result.sort((o1, o2) -> Integer.compare(o2.getUsers().size(), o1.getUsers().size()));
        return groupMapper.groupListToGroupDtoList(result);
    }

    @Override
    public GroupDto findGroupById(int id) {
        log.debug(String.format("Call method findGroupById with id %d", id));
        Group group;
        try {
            group = groupRepository.findById(id).get();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GroupNotFoundException(e);
        }
        return groupMapper.groupToGroupDto(group);
    }

    @Override
    @Transactional
    public void addUserToGroup(int userId, int groupId) {
        log.debug(String.format("Call method addUserToGroup with userId %d, groupId %d", userId, groupId));
        try {
            User user = userRepository.findById(userId).get();
            Group group = groupRepository.findById(groupId).get();
            group.getUsers().add(user);
            user.getGroups().add(group);
            groupRepository.save(group);
            userRepository.save(user);
            log.info(String.format("User %s successfully added to group %s", user.getUserPrincipal().getUsername(), group.getTitle()));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new AddUserToGroupException(e);
        }
    }

    @Override
    @Transactional
    public void removeUserFromGroup(int userId, int groupId) {
        log.debug(String.format("Call method removeUserFromGroup with userId %d, groupId %d", userId, groupId));
        try {
            User user = userRepository.findById(userId).get();
            Group group = groupRepository.findById(groupId).get();
            group.getUsers().remove(user);
            user.getGroups().remove(group);
            groupRepository.save(group);
            userRepository.save(user);
            log.info(String.format("User %s successfully removed to group %s", user.getUserPrincipal().getUsername(), group.getTitle()));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RemoveUserFromGroupException(e);
        }
    }

}
