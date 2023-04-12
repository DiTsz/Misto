package com.example.QuestMisto.Service;

import com.example.QuestMisto.Model.City;
import com.example.QuestMisto.Model.CityName;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CityService {
    City findByCityName(CityName cityName);

    List<City> findAll();

    City findById(UUID uuid);

    void save(City city);
}

