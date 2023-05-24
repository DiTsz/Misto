package com.example.QuestMisto.models.entities;

import com.example.QuestMisto.models.enums.Difficulty;
import com.example.QuestMisto.models.enums.QuestType;
import com.example.QuestMisto.models.enums.TypeReward;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "quest")
public class Quest {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "quest_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID Id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(45)", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "difficulty", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Difficulty difficulty;

    @Column(name = "type_reward", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TypeReward typeReward;

    @Column(name = "quest_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private QuestType questType;

    @Column(name = "reward", columnDefinition = "VARCHAR(45)", nullable = false)
    private String reward;

    @Column(name = "picture_url", nullable = false, columnDefinition = "TEXT")
    private String pictureUrl;

    @Column(name = "num_of_steps", nullable = false)
    private Integer numOfSteps;

    @Column(name = "distance", nullable = false)
    private Float distance;

    @Column(name = "min_duration", nullable = false)
    private Integer minDuration;

    @Column(name = "max_duration", nullable = false)
    private Integer maxDuration;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;


    @OneToMany(mappedBy = "quest", cascade = CascadeType.MERGE)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "quest", cascade = CascadeType.MERGE)
    private List<FeaturedQuests> feachQuests = new ArrayList<>();

    @OneToMany(mappedBy = "quest", cascade = CascadeType.MERGE)
    private List<CompletedQuests> completedQuests = new ArrayList<>();

    public Quest() {
    }

    public Quest(String name,
                 String description,
                 Difficulty difficulty,
                 TypeReward typeReward,
                 QuestType questType,
                 String reward,
                 String pictureUrl,
                 Integer numOfSteps,
                 Float distance,
                 Integer minDuration,
                 Integer maxDuration,
                 City city) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.typeReward = typeReward;
        this.questType = questType;
        this.reward = reward;
        this.pictureUrl = pictureUrl;
        this.numOfSteps = numOfSteps;
        this.distance = distance;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
        this.city = city;
    }

    public void setDuration(int minDuration, int maxDuration) {
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public TypeReward getTypeReward() {
        return typeReward;
    }

    public void setTypeReward(TypeReward typeReward) {
        this.typeReward = typeReward;
    }

    public QuestType getQuestType() {
        return questType;
    }

    public void setQuestType(QuestType questType) {
        this.questType = questType;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getNumOfSteps() {
        return numOfSteps;
    }

    public void setNumOfSteps(Integer numOfSteps) {
        this.numOfSteps = numOfSteps;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Integer getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(Integer minDuration) {
        this.minDuration = minDuration;
    }

    public Integer getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(Integer maxDuration) {
        this.maxDuration = maxDuration;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<FeaturedQuests> getFeachQuests() {
        return feachQuests;
    }

    public void setFeachQuests(List<FeaturedQuests> feachQuests) {
        this.feachQuests = feachQuests;
    }

    public List<CompletedQuests> getCompletedQuests() {
        return completedQuests;
    }

    public void setCompletedQuests(List<CompletedQuests> completedQuests) {
        this.completedQuests = completedQuests;
    }

    @Override
    public String toString() {
        return "Quest{" +
                "name=' " + name + '\'' +
                ", description=' " + description + '\'' +
                ", difficulty= " + difficulty.name() +
                ", typeReward= " + typeReward.name() +
                ", questType= " + questType.name() +
                ", reward=' " + reward + '\'' +
                ", picture_url='" + pictureUrl + '\'' +
                ", numOfSteps= " + numOfSteps +
                ", distance= " + distance +
                ", minDuration= " + minDuration +
                ", maxDuration= " + maxDuration +
                ", city= " + city +
                /*", ratings= " + ratings +*/
                "}\n";
    }
}
