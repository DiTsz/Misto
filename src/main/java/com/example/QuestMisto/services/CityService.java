package com.example.QuestMisto.services;

import com.example.QuestMisto.interfaces.RepositoryService;
import com.example.QuestMisto.models.entities.City;
import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.enums.CityName;
import com.example.QuestMisto.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CityService implements RepositoryService<City> {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City getByName(String name) {
        return cityRepository.findByCityName(CityName.valueOf(name)).orElse(null);
    }

    public City getByName(CityName name) {
        return cityRepository.findByCityName(name).orElse(null);
    }

    @Override
    public City getById(UUID id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    public void save(City entity) {
        cityRepository.save(entity);

    }

    @Override
    public void delete(City entity) {
        cityRepository.delete(entity);
    }

    @Override
    public City edit(City entity) {
        City existCity = cityRepository.findByCityName(entity.getCityName()).orElse(null);
        if (existCity != null) {
            existCity.setCityName(entity.getCityName());
            existCity.setPictureUrl(entity.getPictureUrl());
            existCity.setDescription((entity.getDescription()));
            existCity.setQuests(entity.getQuests());
            cityRepository.save(existCity);
            return existCity;
        }
        return null;

    }

    public City getByCityName(CityName cityName) {
        return cityRepository.findByCityName(cityName).orElse(null);
    }


}


