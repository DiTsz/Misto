package com.example.QuestMisto.services;

import com.example.QuestMisto.interfaces.RepositoryService;
import com.example.QuestMisto.models.entities.FeaturedQuests;
import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.entities.User;
import com.example.QuestMisto.repositories.FeaturedQuestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FeaturedQuestsService implements RepositoryService<FeaturedQuests> {
    private final FeaturedQuestsRepository featuredQuestsRepository;

    @Autowired
    public FeaturedQuestsService(FeaturedQuestsRepository featuredQuestsRepository) {
        this.featuredQuestsRepository = featuredQuestsRepository;
    }

    @Override
    public List<FeaturedQuests> getAll() {
        return featuredQuestsRepository.findAll();
    }

    @Override
    public FeaturedQuests getByName(String name) {
        return null;
    }

    @Override
    public FeaturedQuests getById(UUID id) {
        return featuredQuestsRepository.findById(id).orElse(null);
    }

    @Override
    public void save(FeaturedQuests entity) {
        FeaturedQuests featuredQuests = featuredQuestsRepository.findByUserAndQuest(entity.getUser(), entity.getQuest());
        if (featuredQuests == null) {
            featuredQuestsRepository.save(entity);
        }else{
            entity=featuredQuests;
            featuredQuestsRepository.save(entity);
        }
    }

    @Override
    public void delete(FeaturedQuests entity) {
        featuredQuestsRepository.delete(entity);
    }

    @Override
    public FeaturedQuests edit(FeaturedQuests entity) {
        return null;
    }

    public void addInFeatured(User user, Quest quest) {
        FeaturedQuests featuredQuest = new FeaturedQuests(quest, user);
        featuredQuestsRepository.save(featuredQuest);
    }

    public void removeFromFeatured(User user, Quest quest) {
        FeaturedQuests featuredQuests = featuredQuestsRepository.findByUserAndQuest(user, quest);
        featuredQuestsRepository.delete(featuredQuests);
    }
}
