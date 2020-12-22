package com.senla.courses.controller;

import com.senla.courses.dto.UserDto;
import com.senla.courses.dto.UserWallMessageDto;
import com.senla.courses.dto.validators.UserDtoValidator;
import com.senla.courses.service.interfaces.UserService;
import com.senla.courses.service.interfaces.UserWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserWallService userWallService;
    @Autowired
    private UserDtoValidator userDtoValidator;

    @GetMapping("")
    public ResponseEntity<?> searchUsers(@RequestParam(name = "country", required = false) String country,
                                         @RequestParam(name = "city", required = false) String city,
                                         @RequestParam(name = "partOfName", required = false) String partOfName,
                                         @RequestParam(name = "lessThanAgeInYears", required = false) Integer lessThanAgeInYears,
                                         @RequestParam(name = "moreThanAgeInYears", required = false) Integer moreThanAgeInYears) {
        List<UserDto> users = userService.searchUsers(country, city, partOfName, lessThanAgeInYears, moreThanAgeInYears);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable("id") int id) {
        UserDto user = userService.getUserInfo(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto user, BindingResult result) {
        userDtoValidator.validate(user, result);
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField())
                    .append(" ")
                    .append(fe.getDefaultMessage())
                    .append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            userService.registerUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateUserInfo(@RequestBody @Valid UserDto user, BindingResult result) {
        userDtoValidator.validate(user, result);
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField())
                    .append(" ")
                    .append(fe.getDefaultMessage())
                    .append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            userService.updateUserInfo(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/friends/{id}")
    public ResponseEntity<?> addToFriends(@PathVariable("id") int id) {
        userService.addToFriends(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/friends/{id}")
    public ResponseEntity<?> removeFromFriends(@PathVariable("id") int id) {
        userService.removeFromFriends(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{userId}/wall")
    public ResponseEntity<?> postMessage(@PathVariable("userId") int userId,
                                         @RequestBody @Valid UserWallMessageDto message,
                                         BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            result.getFieldErrors().forEach(fe -> sb.append(fe.getField())
                    .append(" ")
                    .append(fe.getDefaultMessage())
                    .append("<br>"));
            return new ResponseEntity<>(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            userWallService.postMessage(userId, message);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/{userId}/wall/{messageId}")
    public ResponseEntity<?> removeMessage(@PathVariable("userId") int userId, @PathVariable("messageId") int messageId) {
        userWallService.removeMessage(userId, messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
