package com.example.QuestMisto.Service.impl;

import com.example.QuestMisto.Model.City;
import com.example.QuestMisto.Model.CityName;
import com.example.QuestMisto.Repository.CityRepository;
import com.example.QuestMisto.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City findByCityName(CityName cityName) {
        return cityRepository.findByCityName(cityName).orElse(null);
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(UUID uuid) {
        return cityRepository.findById(uuid).orElse(null);
    }
    @Override
    public void save(City city){
        cityRepository.save(city);
    }
}
