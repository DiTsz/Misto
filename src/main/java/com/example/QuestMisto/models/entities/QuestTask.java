package com.example.QuestMisto.models.entities;

import com.example.QuestMisto.models.enums.QuestTaskType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "quest_task")
public class QuestTask {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "task_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID Id;

    @Column(columnDefinition = "TEXT")
    private String taskCondition;


    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "answer", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "answers", nullable = false)
    private List<String> answers = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quest",nullable = false)
    private Quest quest;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "quest_task_type")
    private QuestTaskType questTaskType;

    @Column(columnDefinition = "TEXT")
    private String hint;

    public QuestTask() {
    }

    public QuestTask(String taskCondition, List<String> answers, Quest quest,QuestTaskType questTaskType, String hint) {
        this.taskCondition = taskCondition;
        this.answers = answers;
        this.quest = quest;
        this.questTaskType=questTaskType;
        this.hint=hint;
    }

    public String getTaskCondition() {
        return taskCondition;
    }

    public void setTaskCondition(String taskCondition) {
        this.taskCondition = taskCondition;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public QuestTaskType getQuestTaskType() {
        return questTaskType;
    }

    public void setQuestTaskType(QuestTaskType questTaskType) {
        this.questTaskType = questTaskType;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
