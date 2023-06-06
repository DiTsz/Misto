package com.example.QuestMisto.services;

import com.example.QuestMisto.interfaces.RepositoryService;
import com.example.QuestMisto.models.entities.CompletedQuests;
import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.entities.User;
import com.example.QuestMisto.repositories.CompletedQuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompletedQuestsService implements RepositoryService<CompletedQuests> {
    private final CompletedQuestRepository completedQuestRepository;

    @Autowired
    public CompletedQuestsService(CompletedQuestRepository completedQuestRepository) {
        this.completedQuestRepository = completedQuestRepository;
    }


    @Override
    public List<CompletedQuests> getAll() {
        return completedQuestRepository.findAll();
    }

    @Override
    public CompletedQuests getByName(String name) {
        return null;
    }

    @Override
    public CompletedQuests getById(UUID id) {
        return completedQuestRepository.findById(id).orElse(null);
    }

    @Override
    public void save(CompletedQuests entity) {
        CompletedQuests completedQuests = completedQuestRepository.findByUserAndQuest(entity.getUser(), entity.getQuest());
        if (completedQuests == null)
            completedQuestRepository.save(entity);
        else {
            entity = completedQuests;
            completedQuestRepository.save(entity);
        }
    }

    @Override
    public void delete(CompletedQuests entity) {
        completedQuestRepository.delete(entity);
    }

    @Override
    public CompletedQuests edit(CompletedQuests entity) {
        return null;
    }

    public List<CompletedQuests> getAllByUser(User user) {
        return completedQuestRepository.findAllByUser(user);
    }

    public CompletedQuests getAllByUserAndQuest(User user, Quest quest) {
        return completedQuestRepository.findByUserAndQuest(user, quest);
    }
    public int countCompletedQuestsByUser(User user){
        return completedQuestRepository.countByUser(user);
    }
}
