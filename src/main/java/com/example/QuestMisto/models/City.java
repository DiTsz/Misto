package com.example.QuestMisto.models;

import com.example.QuestMisto.models.enums.CityName;
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
@Table(name = "city")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @javax.persistence.Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "city_id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID Id;

    @Column(name = "city_name",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private CityName cityName;

    @Column (name="description")
    private String description;

    @Column(name = "picture_url", columnDefinition = "TEXT")
    private String pictureUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = CascadeType.ALL)
    private List<Quest> quests = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       City city = (City) o;

        return Id == city.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

}
