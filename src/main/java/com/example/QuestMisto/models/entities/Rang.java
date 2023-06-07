package com.example.QuestMisto.models.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "rang")
public class Rang {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "rang_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID Id;
    @Column(nullable = false,columnDefinition = "VARCHAR(50)",unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "rang", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();
    private int requiredNumOfCompQuests;

    public Rang() {
    }

    public Rang(String rankName, int reqiredNumOfCompQuests) {
        this.name = rankName;
        this.requiredNumOfCompQuests = reqiredNumOfCompQuests;
    }

    public String getRankName() {
        return name;
    }

    public void setRankName(String rankName) {
        this.name = rankName;
    }

    public int getRequiredNumOfCompQuests() {
        return requiredNumOfCompQuests;
    }

    public void setRequiredNumOfCompQuests(int requiredNumOfCompQuests) {
        this.requiredNumOfCompQuests = requiredNumOfCompQuests;
    }
}
