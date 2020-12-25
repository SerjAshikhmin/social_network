package com.senla.cources.controller;

import com.senla.cources.dto.PrivateMessageDto;
import com.senla.cources.service.interfaces.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> sendMessage(@PathVariable("receiverId") int receiverId,
                                         @RequestBody @Valid PrivateMessageDto message, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField())
                    .append(" ")
                    .append(fe.getDefaultMessage())
                    .append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            privateMessageService.sendMessage(message, receiverId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
