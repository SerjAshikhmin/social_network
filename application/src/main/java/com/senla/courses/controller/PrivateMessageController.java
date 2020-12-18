package com.senla.courses.controller;

import com.senla.courses.dto.PrivateMessageDto;
import com.senla.courses.service.interfaces.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/private-messages")
public class PrivateMessageController {

    @Autowired
    private PrivateMessageService privateMessageService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> showDialog(@PathVariable("userId") int userId) {
        List<PrivateMessageDto> messages = privateMessageService.showDialog(userId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/{receiverId}")
    public ResponseEntity<?> sendMessage(@PathVariable("receiverId") int receiverId, @RequestBody @Valid PrivateMessageDto message) {
        privateMessageService.sendMessage(message, receiverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
