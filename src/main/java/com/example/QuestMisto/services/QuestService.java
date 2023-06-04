package com.example.QuestMisto.services;

import com.example.QuestMisto.comparators.RatingComparator;
import com.example.QuestMisto.interfaces.RepositoryService;
import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.enums.CityName;
import com.example.QuestMisto.repositories.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class QuestService implements RepositoryService<Quest> {

    private final QuestRepository questRepository;


    @Autowired
    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @Override
    public List<Quest> getAll() {
        RatingComparator ratingComparator = new RatingComparator();
        //return questRepository.findAll();
        return questRepository.findAll().stream().sorted(ratingComparator).collect(Collectors.toList());

    }
    @Override
    public Quest getById(UUID id) {
        return questRepository.findById(id).orElse(null);
    }

    @Override
    public Quest getByName(String name) {
        return questRepository.findByName(name);
    }

    @Override
    public void save(Quest entity) {
        questRepository.save(entity);
    }

    @Override
    public void delete(Quest entity) {
        questRepository.delete(entity);
    }

    @Override
    public Quest edit(Quest entity) {
        return null;
    }

    public List<Quest> getByCityNameSortedByRating(CityName cityName){
        RatingComparator ratingComparator = new RatingComparator();
        return questRepository
                .findAll()
                .stream()
                .filter(i -> i.getCity().getCityName().equals(cityName))
                .sorted(ratingComparator)
                .limit(5)
                .toList();

    }
     public List<Quest> getFirst5OrderByRatings(){
         List<Quest> quests = new ArrayList<>();
         for(UUID id: questRepository.findFirst5IdsOrderByRating()){
             Quest quest = questRepository.findById(id).get();
             quests.add(quest);
         }
        return quests;
    }

}
