package com.example.QuestMisto.services;

import com.example.QuestMisto.interfaces.RepositoryService;
import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.entities.Rating;
import com.example.QuestMisto.models.entities.User;
import com.example.QuestMisto.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingService implements RepositoryService<Rating> {
    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    @Override
    public List<Rating> getAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getByName(String name) {
        return null;
    }

    @Override
    public Rating getById(UUID id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Rating entity) {
        Rating rating = ratingRepository.findByUserAndQuest(entity.getUser(),entity.getQuest());
        if(rating==null) {
            ratingRepository.save(entity);
        }else{
            rating=entity;
            ratingRepository.save(rating);
        }
    }

    @Override
    public void delete(Rating entity) {
        ratingRepository.delete(entity);
    }

    @Override
    public Rating edit(Rating entity) {
        return null;
    }

    public List<Rating> getAllByRating(Integer rating){
        return ratingRepository.findAllByRating(rating);
    }
    public Rating getByUserAndQuest(User user, Quest quest){
        return ratingRepository.findByUserAndQuest(user,quest);
    }
}
