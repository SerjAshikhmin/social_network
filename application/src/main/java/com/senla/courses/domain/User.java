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
    @GeneratedValue
    @Column(name = "users_id", length = 10)
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "users_friends",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "friends_id")})
    private List<User> friends;

    @ManyToMany(mappedBy = "friends", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<User> inFriends;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userWall_id", referencedColumnName = "id")
    private UserWall userWall;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PrivateMessage> outgoingMessages;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PrivateMessage> incomingMessages;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Group> groups;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userPrincipal_id", referencedColumnName = "id")
    private MyUserPrincipal userPrincipal;
}
