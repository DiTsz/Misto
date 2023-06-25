package com.example.QuestMisto.models.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Table(name = "completed_tasks")
public class CompletedTasks {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "comp_tsk_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="task")
    private QuestTask task;
    public CompletedTasks() {
    }

    public CompletedTasks(User user, QuestTask quest) {
        this.user = user;
        this.task=quest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public QuestTask getQuestTask() {
        return task;
    }

    public void setQuest(QuestTask quest) {
        this.task = quest;
    }

    @Override
    public String toString() {
        return "CompletedTasks{" +
                "user=" + user.getEmail() +
                ", quest="+ task.getTaskCondition() +
                '}';
    }
}
