package com.example.QuestMisto.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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

    @Column(name = "reward", columnDefinition = "VARCHAR(45)",nullable = false)
    private String reward;

    @Column(name = "city",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private City city;

    @Column(name = "picture_url", nullable = false,columnDefinition = "TEXT")
    private String picture_url;
}
