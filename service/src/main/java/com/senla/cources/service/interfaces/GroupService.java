package com.senla.cources.service.interfaces;

import com.senla.cources.dto.GroupDto;
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
