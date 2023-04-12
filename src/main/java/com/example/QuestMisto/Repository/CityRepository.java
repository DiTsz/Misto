package com.example.QuestMisto.Repository;

import com.example.QuestMisto.Model.City;
import com.example.QuestMisto.Model.CityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CityRepository extends JpaRepository<City, UUID> {
    Optional<City> findByCityName(CityName cityName);
    List<City> findAll();
    Optional<City> findById(UUID uuid);
}
