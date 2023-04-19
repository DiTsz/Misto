package com.example.QuestMisto.services;

import com.example.QuestMisto.interfaces.RepositoryService;
import com.example.QuestMisto.models.City;
import com.example.QuestMisto.models.enums.CityName;
import com.example.QuestMisto.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CityService implements RepositoryService {
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

    @Override
    public City getById(UUID id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Object entity) {
        cityRepository.save((City) entity);
    }

    @Override
    public void delete(Object entity) {
        cityRepository.delete((City) entity);

    }
}


