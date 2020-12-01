package com.senla.courses.domain;

import com.senla.courses.domain.enums.Gender;
import com.senla.courses.domain.security.MyUserPrincipal;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "users_id")
    private int id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birthDate;
    private String country;
    private String city;
    private String personalInfo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_friends",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "friends_id")})
    private List<User> friends;

    @ManyToMany(mappedBy = "friends", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> inFriends;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userWall_id", referencedColumnName = "id")
    private UserWall userWall;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PrivateMessage> outgoingMessages;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PrivateMessage> incomingMessages;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Group> groups;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userPrincipal_id", referencedColumnName = "id")
    private MyUserPrincipal userPrincipal;
}
