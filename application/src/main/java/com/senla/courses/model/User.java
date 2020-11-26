package com.senla.courses.model;

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
    private LocalDate birthDate;
    private String personalInfo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_friends",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "friends_id")})
    private List<User> friends;

    @ManyToMany(mappedBy = "friends", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> inFriends;

    @OneToOne
    @PrimaryKeyJoinColumn
    private UserWall userWall;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PrivateMessage> outgoingMessages;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<PrivateMessage> incomingMessages;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Group> groups;

    @OneToOne
    @PrimaryKeyJoinColumn
    private UserPrincipal userPrincipal;
}
