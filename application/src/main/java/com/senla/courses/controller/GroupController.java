package com.senla.courses.controller;

import com.senla.courses.dto.GroupDto;
import com.senla.courses.dto.GroupWallMessageDto;
import com.senla.courses.service.interfaces.GroupService;
import com.senla.courses.service.interfaces.GroupWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupWallService groupWallService;

    @GetMapping("")
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<GroupDto> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> findGroupById(@PathVariable(name = "id") int id) {
        GroupDto group = groupService.findGroupById(id);
        return ResponseEntity.ok(group);
    }

    @PostMapping("")
    public ResponseEntity<?> addGroup(@RequestBody GroupDto group) {
        groupService.addGroup(group);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeGroup(@PathVariable("id") int id) {
        groupService.removeGroup(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{groupId}/users/{userId}")
    public ResponseEntity<?> addUserToGroup(@PathVariable("groupId") int groupId, @PathVariable("userId") int userId) {
        groupService.addUserToGroup(userId, groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{groupId}/users/{userId}")
    public ResponseEntity<?> removeUserFromGroup(@PathVariable("groupId") int groupId, @PathVariable("userId") int userId) {
        groupService.removeUserFromGroup(userId, groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{groupId}/wall")
    public ResponseEntity<?> postMessage(@PathVariable("groupId") int groupId, @RequestBody GroupWallMessageDto message) {
        groupWallService.postMessage(groupId, message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{groupId}/wall/{messageId}")
    public ResponseEntity<?> removeMessage(@PathVariable("groupId") int groupId, @PathVariable("messageId") int messageId) {
        groupWallService.removeMessage(groupId, messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
