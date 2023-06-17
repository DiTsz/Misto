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
//@IdClass(QuestTaskKey.class)
public class QuestTask {
     @Id
     @GeneratedValue(generator = "uuid2")
     @GenericGenerator(name = "uuid2", strategy = "uuid2")
     @Column(name = "task_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
     @Type(type = "uuid-char")
     private UUID Id;
    //@Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quest", nullable = false)
    private Quest quest;
    //@Id
    @Column(name = "orders")
    private int orders;
    @Column(columnDefinition = "TEXT")
    private String taskCondition;

    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "answer", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "answers", nullable = false)
    private List<String> answers = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "quest_task_type", nullable = false)
    private QuestTaskType questTaskType;

    @Column(columnDefinition = "TEXT")
    private String hint;


    public QuestTask() {
    }

    public QuestTask(String taskCondition,
                     List<String> answers,
                     Quest quest,
                     QuestTaskType questTaskType,
                     String hint,
                     int orders) {
        this.taskCondition = taskCondition;
        this.answers = answers;
        this.quest = quest;
        this.questTaskType = questTaskType;
        this.hint = hint;
        this.orders = orders;
    }

    public String getTaskCondition() {
        return taskCondition;
    }

    public void setTaskCondition(String taskCondition) {
        this.taskCondition = taskCondition;
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

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "QuestTask{" +
                "taskCondition='" + taskCondition + '\'' +
                ", quest=" + quest.getName() +
                ", questTaskType=" + questTaskType.name() +
                ", orders=" + orders +
                '}';
    }
}
