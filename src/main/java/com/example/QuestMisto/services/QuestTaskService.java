package com.example.QuestMisto.services;

import com.example.QuestMisto.interfaces.RepositoryService;
import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.entities.QuestTask;
import com.example.QuestMisto.repositories.QuestTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuestTaskService implements RepositoryService<QuestTask> {

    private final QuestTaskRepository questTaskRepository;

    @Autowired
    public QuestTaskService(QuestTaskRepository questTaskRepository) {
        this.questTaskRepository = questTaskRepository;
    }

    @Override
    public List<QuestTask> getAll() {
        return questTaskRepository.findAll();
    }

    @Override
    public QuestTask getByName(String name) {
        return null;
    }

    @Override
    public QuestTask getById(UUID id) {
        return questTaskRepository.findById(id).orElse(null);
    }

    @Override
    public void save(QuestTask entity) {
        questTaskRepository.save(entity);
    }

    @Override
    public void delete(QuestTask entity) {
        questTaskRepository.delete(entity);
    }

    @Override
    public QuestTask edit(QuestTask entity) {
        return null;
    }
    public List<QuestTask> getAllByQuest(Quest quest){
        return questTaskRepository.findAllByQuest(quest);
    }
}
