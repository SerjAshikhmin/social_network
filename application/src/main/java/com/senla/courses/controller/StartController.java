package com.senla.courses.controller;

import com.senla.courses.domain.*;
import com.senla.courses.domain.enums.Gender;
import com.senla.courses.domain.security.MyUserPrincipal;
import com.senla.courses.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;


@Controller
public class StartController {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupWallService groupWallService;
    @Autowired
    private PrivateMessageService privateMessageService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserWallService userWallService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String testPage() {
        //groupService.addGroup(new Group(4, "bhghgff", null, null));
        //groupService.addUserToGroup(1, 3);
        //groupService.removeGroup(3);
        //groupService.removeUserFromGroup(1, 3);
        //groupWallService.postMessage(2, new GroupWallMessage(6, "kjf", LocalDateTime.of(2020, Month.NOVEMBER, 25, 10, 0), null));
        //groupWallService.removeMessage(2, 6);
        //userWallService.postMessage(1, new UserWallMessage(88, "hjkhjf", LocalDateTime.of(2020, Month.NOVEMBER, 25, 10, 0), null));
        //userWallService.removeMessage(1, 88);
        //String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        //userService.addToFriends(6);
        //userService.removeFromFriends(6);
        //User user = userService.getUserInfo(10);
        /*User user = new User(11, "Format", "Formatter", Gender.male, LocalDate.of(2000, Month.NOVEMBER, 25),
                            "Russia", "Orel", "format info", null, null, null, null, null, null, null);
        MyUserPrincipal userPrincipal = new MyUserPrincipal(11, "formatt", "pass11", user, null);
        userService.registerUser(user, userPrincipal);*/
        /*User user = new User(11, "Format", "Formatov", Gender.male, LocalDate.of(1999, Month.NOVEMBER, 25),
                "Russia", "Orel", "format info", null, null, null, null, null, null, null);
        MyUserPrincipal userPrincipal = new MyUserPrincipal(11, "formatt", "pass112", user, null);
        user.setUserPrincipal(userPrincipal);
        userService.updateUserInfo(user);*/
        //List<User> users = userService.searchUsers(null, null, null, 27, 24);
        /*privateMessageService.sendMessage(new PrivateMessage(6, "user 1 to user 2, message 5",
                LocalDateTime.of(2020, Month.NOVEMBER, 25, 10, 0), null, null), 2);*/
        //List<PrivateMessage> privateMessages = privateMessageService.showDialog(2);
        List<Group> groups = groupService.getAllGroups();
        System.out.println(groups);
        return "index";
    }

}
