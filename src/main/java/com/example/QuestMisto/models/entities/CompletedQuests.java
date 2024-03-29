package com.example.QuestMisto.models.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "completed_quests")
public class CompletedQuests {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "comp_qst_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="quest")
    private Quest quest;
    public CompletedQuests() {
    }

    public CompletedQuests(User user, Quest quest) {
        this.user = user;
        this.quest=quest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    @Override
    public String toString() {
        return "CompletedQuests{" +
                "user=" + user.getEmail() +
                ", quest="+ quest.getName() +
                '}';
    }
}
