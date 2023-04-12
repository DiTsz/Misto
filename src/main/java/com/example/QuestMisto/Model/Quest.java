package com.example.QuestMisto.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "quest")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quest {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "quest_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID Id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(45)")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "difficulty",nullable = false)
    private Integer difficulty;

    @Column (name = "type_reward",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TypeReward typeReward;

    @Column (name = "quest_type",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private QuestType questType;

    @Column(name = "reward", columnDefinition = "VARCHAR(45)",nullable = false)
    private String reward;

    @Column(name = "picture_url", nullable = false,columnDefinition = "TEXT")
    private String picture_url;

    @Column(name = "rating",nullable = false)
    private Integer rating;

    @Column(name = "num_of_steps",nullable = false)
    private Integer numOfSteps;

    @Column (name = "distance",nullable = false)
    private Float distance;

    @Column (name = "duration", nullable = false)
    private String duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id",nullable = false)
    private City city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quest quest = (Quest) o;
        return name.equals(quest.name) &&
                Objects.equals(description, quest.description) &&
                difficulty.equals(quest.difficulty) &&
                typeReward == quest.typeReward &&
                questType == quest.questType &&
                reward.equals(quest.reward) &&
                picture_url.equals(quest.picture_url) &&
                rating.equals(quest.rating) &&
                numOfSteps.equals(quest.numOfSteps) &&
                distance.equals(quest.distance) &&
                duration.equals(quest.duration) &&
                city.equals(quest.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,
                description,
                difficulty,
                typeReward,
                questType,
                reward,
                picture_url,
                rating,
                numOfSteps,
                distance,
                duration,
                city);
    }
}
