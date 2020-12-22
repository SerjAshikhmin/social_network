package com.senla.courses.utils;

import com.senla.courses.domain.*;
import com.senla.courses.domain.enums.Gender;
import com.senla.courses.domain.security.MyUserPrincipal;
import com.senla.courses.domain.security.Role;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Getter
public class TestData {

    private final List<Group> testGroupList;
    private final List<User> testUserList;
    private final List<GroupWallMessage> testGroupWallMessages;
    private final List<UserWallMessage> testUserWallMessages;
    private final List<PrivateMessage> testPrivateMessages;
    private final List<MyUserPrincipal> testUserPrincipals;
    private final Role defaultTestRole;

    public TestData() {
        testGroupList = new ArrayList<>();
        Group firstGroup = new Group(1, "Kinomania", new HashSet<>(), new GroupWall());
        testGroupList.add(firstGroup);
        Group secondGroup = new Group(2, "Cherniy umor", new HashSet<>(), new GroupWall());
        testGroupList.add(secondGroup);
        Group thirdGroup = new Group(3, "Ostroumnie", new HashSet<>(), new GroupWall());
        testGroupList.add(thirdGroup);
        firstGroup.getGroupWall().setMessages(new ArrayList<>());
        firstGroup.getGroupWall().setGroup(firstGroup);

        testGroupWallMessages = new ArrayList<>();
        testGroupWallMessages.add(new GroupWallMessage(1, "group 1, wall message 1",
                LocalDateTime.of(2020, Month.OCTOBER, 17, 10, 37, 45), null));
        testGroupWallMessages.add(new GroupWallMessage(2, "group 1, wall message 2",
                LocalDateTime.of(2020, Month.OCTOBER, 18, 18, 39, 2), null));
        testGroupWallMessages.add(new GroupWallMessage(3, "group 1, wall message 3",
                LocalDateTime.of(2020, Month.NOVEMBER, 27, 15, 12, 35), null));

        User firstUser = new User(1, "Michael", "Astakhov", Gender.male, LocalDate.of(1993, Month.APRIL, 27),
                "Russia", "Moscow", "some info about me", new ArrayList<>(), new UserWall(), null, null, new HashSet<>(), null);
        MyUserPrincipal firstPrincipal = new MyUserPrincipal(1, "anonymousUser", "pass1", firstUser, new HashSet<>());
        defaultTestRole = new Role(1, "user", new HashSet<>());
        defaultTestRole.getUsersPrincipals().add(firstPrincipal);
        firstPrincipal.getRoles().add(defaultTestRole);
        firstUser.setUserPrincipal(firstPrincipal);
        firstUser.getUserWall().setMessages(new ArrayList<>());
        firstUser.getUserWall().setUser(firstUser);

        User secondUser = new User(2, "Daria", "Alimova", Gender.female, LocalDate.of(1996, Month.JUNE, 13),
                "Russia", "Moscow", "some more info about me", new ArrayList<>(), new UserWall(), null, null, new HashSet<>(), null);
        MyUserPrincipal secondPrincipal = new MyUserPrincipal(2, "ali_daria", "pass2", firstUser, new HashSet<>());
        secondUser.setUserPrincipal(secondPrincipal);

        User thirdUser = new User(3, "Sergey", "Lapeev", Gender.male, LocalDate.of(1988, Month.NOVEMBER, 17),
                "Russia", "St. Petersburg", "some more info about me", new ArrayList<>(), new UserWall(), null, null, new HashSet<>(), null);
        MyUserPrincipal thirdPrincipal = new MyUserPrincipal(3, "lap_ser", "pass3", firstUser, new HashSet<>());
        thirdUser.setUserPrincipal(thirdPrincipal);
        firstUser.getFriends().add(thirdUser);

        testUserList = new ArrayList<>();
        testUserList.add(firstUser);
        testUserList.add(secondUser);
        testUserList.add(thirdUser);

        testUserPrincipals = new ArrayList<>();
        testUserPrincipals.add(firstPrincipal);
        testUserPrincipals.add(secondPrincipal);
        testUserPrincipals.add(thirdPrincipal);

        testUserWallMessages = new ArrayList<>();
        testUserWallMessages.add(new UserWallMessage(1, "user 1, wall message 1",
                LocalDateTime.of(2020, Month.NOVEMBER, 25, 10, 37, 45), null));
        testUserWallMessages.add(new UserWallMessage(2, "user 1, wall message 2",
                LocalDateTime.of(2020, Month.NOVEMBER, 25, 18, 39, 2), null));
        testUserWallMessages.add(new UserWallMessage(3, "user 1, wall message 3",
                LocalDateTime.of(2020, Month.NOVEMBER, 27, 15, 12, 35), null));

        testPrivateMessages = new ArrayList<>();
        testPrivateMessages.add(new PrivateMessage(1, "user 1 to user 2, message 1",
                LocalDateTime.of(2020, Month.OCTOBER, 17, 10, 37, 45), firstUser, secondUser));
        testPrivateMessages.add(new PrivateMessage(2, "user 1 to user 2, message 2",
                LocalDateTime.of(2020, Month.OCTOBER, 18, 18, 39, 2), firstUser, secondUser));
        testPrivateMessages.add(new PrivateMessage(3, "user 2 to user 1, message 1",
                LocalDateTime.of(2020, Month.NOVEMBER, 27, 15, 12, 35), secondUser, firstUser));
        testPrivateMessages.get(2).setRead(true);
    }

    public List<User> getTestUsersWithCountryAndCity() {
        return new ArrayList<>(Arrays.asList(testUserList.get(2)));
    }

    public List<User> getTestUsersWithPartOfName() {
        return new ArrayList<>(Arrays.asList(testUserList.get(0)));
    }

    public List<User> getTestUsersWithAge() {
        return new ArrayList<>(Arrays.asList(testUserList.get(0), testUserList.get(1)));
    }
}
