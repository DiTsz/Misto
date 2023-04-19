package com.example.QuestMisto.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "rating_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id",nullable = false)
    private Quest quest;

    @Column(name = "rating",nullable = false)
    private Integer rating;
}
