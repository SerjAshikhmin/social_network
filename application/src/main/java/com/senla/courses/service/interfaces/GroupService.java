package com.senla.courses.service.interfaces;

import com.senla.courses.domain.Group;
import com.senla.courses.dto.GroupDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {

    void addGroup(GroupDto group);
    void removeGroup(int id);
    List<GroupDto> getAllGroups();
    GroupDto findGroupById(int id);
    void addUserToGroup(int userId, int groupId);
    void removeUserFromGroup(int userId, int groupId);
}
