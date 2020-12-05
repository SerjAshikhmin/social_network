package com.senla.courses.service;

import com.senla.courses.domain.Group;
import com.senla.courses.domain.GroupWall;
import com.senla.courses.domain.User;
import com.senla.courses.dto.GroupDto;
import com.senla.courses.dto.mappers.GroupMapper;
import com.senla.courses.repository.GroupRepository;
import com.senla.courses.repository.UserRepository;
import com.senla.courses.service.interfaces.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;


@Slf4j
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupMapper groupMapper;

    @Override
    @Transactional
    public void addGroup(GroupDto groupDto) {
        try {
            Group group = groupMapper.groupDtoToGroup(groupDto);
            group.setGroupWall(new GroupWall());
            group.setUsers(new HashSet<>());
            groupRepository.save(group);
            log.info(String.format("Group %s successfully created", group.getTitle()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void removeGroup(int id) {
        try {
            Group group = groupRepository.findById(id).get();
            for (User user : group.getUsers()) {
                for (Group userGroup : user.getGroups()) {
                    if (userGroup.getId() == group.getId()) {
                        user.getGroups().remove(group);
                    }
                }
            }
            groupRepository.delete(group);
            log.info(String.format("Group %s successfully removed", group.getTitle()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<GroupDto> getAllGroups() {
        List<Group> result = null;
        try {
            result = groupRepository.findAll();
            if (result == null || result.isEmpty()) {
                throw new RuntimeException("Groups not found");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        result.sort((o1, o2) -> Integer.valueOf(o2.getUsers().size()).compareTo(Integer.valueOf(o1.getUsers().size())));
        return groupMapper.groupListToGroupDtoList(result);
    }

    @Override
    public GroupDto findGroupById(int id) {
        Group group = null;
        try {
            group = groupRepository.findById(id).get();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return groupMapper.groupToGroupDto(group);
    }

    @Override
    @Transactional
    public void addUserToGroup(int userId, int groupId) {
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
        }
    }

    @Override
    @Transactional
    public void removeUserFromGroup(int userId, int groupId) {
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
        }
    }
}
