package com.example.QuestMisto.models.entities;

import com.example.QuestMisto.models.enums.CityName;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "city")
public class City {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "city_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID Id;

    @Column(name = "city_name",nullable = false, unique = true)
    @Enumerated(EnumType.ORDINAL)
    private CityName cityName;

    @Column (name="description")
    private String description;

    @Column(name = "picture_url", columnDefinition = "TEXT")
    private String pictureUrl;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "city", cascade = CascadeType.ALL)
    private List<Quest> quests = new ArrayList<>();

    public City() {
    }

    public City(CityName cityName, String description, String pictureUrl) {
        this.cityName = cityName;
        this.description = description;
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityName=" + cityName.name() +
                ", description='" + description + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                '}';
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public CityName getCityName() {
        return cityName;
    }

    public void setCityName(CityName cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }
}
