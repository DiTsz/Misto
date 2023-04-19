package com.example.QuestMisto.models;

import com.example.QuestMisto.models.enums.Difficulty;
import com.example.QuestMisto.models.enums.QuestType;
import com.example.QuestMisto.models.enums.TypeReward;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
    @Enumerated(EnumType.ORDINAL)
    private Difficulty difficulty;

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

    @Column(name = "num_of_steps",nullable = false)
    private Integer numOfSteps;

    @Column (name = "distance",nullable = false)
    private Float distance;

    @Column (name = "min_duration", nullable = false)
    private Integer minDuration;

    @Column (name = "max_duration", nullable = false)
    private Integer maxDuration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id",nullable = false)
    private City city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quest", cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

}
