package com.example.QuestMisto.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_avatar")
public class UserAvatar {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "avatar_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID Id;

    @Column(name = "name", columnDefinition = "VARCHAR(45)")
    private String name;

    @Column(name = "picture_url",columnDefinition = "TEXT")
    private String pictureUrl;

    @Column(name = "required_xp")
    private Integer requiredXp;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userAvatar", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    public UserAvatar() {
    }

    public UserAvatar(String name, String pictureUrl, Integer requiredXp) {
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.requiredXp = requiredXp;
    }


    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getRequiredXp() {
        return requiredXp;
    }

    public void setRequiredXp(Integer requiredXp) {
        this.requiredXp = requiredXp;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
