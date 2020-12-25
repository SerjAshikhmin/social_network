package com.senla.cources.domain;

import com.senla.cources.domain.enums.Gender;
import com.senla.cources.domain.security.MyUserPrincipal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @Column(nullable = false, length = 20)
    private String firstName;
    @NotNull
    @Column(nullable = false, length = 20)
    private String lastName;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;
    private LocalDate birthDate;
    @Column(length = 45)
    private String country;
    @Column(length = 20)
    private String city;
    @Column(length = 1000)
    private String personalInfo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "users_friends",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name = "friends_id")})
    private List<User> friends;

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
