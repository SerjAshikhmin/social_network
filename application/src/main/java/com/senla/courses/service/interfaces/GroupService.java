package com.senla.courses.service.interfaces;

import com.senla.courses.domain.Group;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {

    void addGroup(Group group);
    void removeGroup(int id);
    List<Group> getAllGroups();
    void addUserToGroup(int userId, int groupId);
    void removeUserFromGroup(int userId, int groupId);
}
