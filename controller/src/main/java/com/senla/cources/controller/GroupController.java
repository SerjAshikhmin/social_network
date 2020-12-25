package com.senla.cources.controller;

import com.senla.cources.dto.GroupDto;
import com.senla.cources.dto.GroupWallMessageDto;
import com.senla.cources.service.interfaces.GroupService;
import com.senla.cources.service.interfaces.GroupWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> addGroup(@RequestBody @Valid GroupDto group, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField())
                    .append(" ")
                    .append(fe.getDefaultMessage())
                    .append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            groupService.addGroup(group);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
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
    public ResponseEntity<?> postMessage(@PathVariable("groupId") int groupId,
                                         @RequestBody @Valid GroupWallMessageDto message, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField())
                                                    .append(" ")
                                                    .append(fe.getDefaultMessage())
                                                    .append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            groupWallService.postMessage(groupId, message);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{groupId}/wall/{messageId}")
    public ResponseEntity<?> removeMessage(@PathVariable("groupId") int groupId, @PathVariable("messageId") int messageId) {
        groupWallService.removeMessage(groupId, messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
