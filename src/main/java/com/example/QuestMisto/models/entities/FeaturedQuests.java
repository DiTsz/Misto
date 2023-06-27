package com.example.QuestMisto.models.entities;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "featured_quests")
@EqualsAndHashCode
public class FeaturedQuests {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "feach_qsts_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quest",nullable = false)
    private Quest quest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user",nullable = false)
    private User user;

    public FeaturedQuests() {
    }

    public FeaturedQuests(Quest quest, User user) {
        this.quest = quest;
        this.user = user;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
