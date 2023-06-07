package com.example.QuestMisto.models.entities;

import com.example.QuestMisto.models.enums.AuthProvider;
import com.example.QuestMisto.models.enums.Role;
import com.example.QuestMisto.models.enums.Status;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID Id;

    @Column(name = "username", columnDefinition = "VARCHAR(100)", nullable = false,unique = true)
    private String username;
    @Column(name = "name", columnDefinition = "VARCHAR(100)")
    private String name;
    @Column(name = "last_name", columnDefinition = "VARCHAR(100)")
    private String lastName;

    @Column(name = "password", columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "email", columnDefinition = "VARCHAR(50)", nullable = false,unique = true)
    private String email;

    @Column (name = "role", nullable = false)
    private Role role;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name ="auth_provider")
    @Enumerated(EnumType.ORDINAL)
    private AuthProvider authProvider;

    @Column(name = "gem_balance",nullable = false)
    private int gemBalance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE)
    private List<FeaturedQuests> feachQuests = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.MERGE)
    private List<CompletedQuests> completedQuest = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "avatar_id",nullable = false)
    private UserAvatar userAvatar;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rang_id",nullable = false)
    private Rang rang;
    public User() {
        this.gemBalance=0;
    }

    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.gemBalance=0;
        this.status=Status.ACTIVE;
    }

    public User(String username,
                String name,
                String lastName,
                String password,
                String email) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.gemBalance=0;
        this.status=Status.ACTIVE;
        this.role=Role.USER;

    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AuthProvider getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public UserAvatar getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(UserAvatar userAvatar) {
        this.userAvatar = userAvatar;
    }

    public List<FeaturedQuests> getFeachQuests() {
        return feachQuests;
    }

    public void setFeachQuests(List<FeaturedQuests> feachQuests) {
        this.feachQuests = feachQuests;
    }

    public List<CompletedQuests> getCompletedQuest() {
        return completedQuest;
    }

    public void setCompletedQuest(List<CompletedQuests> completedQuest) {
        this.completedQuest = completedQuest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGemBalance() {
        return gemBalance;
    }

    public void setGemBalance(int gemBalance) {
        this.gemBalance = gemBalance;
    }

    public Rang getRang() {
        return rang;
    }

    public void setRang(Rang rang) {
        this.rang = rang;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", ratings=" + ratings +
                '}';
    }
}
