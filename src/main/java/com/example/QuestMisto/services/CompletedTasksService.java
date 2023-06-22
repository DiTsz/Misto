package com.example.QuestMisto.services;

import com.example.QuestMisto.interfaces.RepositoryService;
import com.example.QuestMisto.models.entities.CompletedQuests;
import com.example.QuestMisto.models.entities.CompletedTasks;
import com.example.QuestMisto.repositories.CompletedTasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompletedTasksService implements RepositoryService<CompletedTasks> {
    private final CompletedTasksRepository completedTasksRepository;

    @Autowired
    public CompletedTasksService(CompletedTasksRepository completedTasksRepository) {
        this.completedTasksRepository = completedTasksRepository;
    }

    @Override
    public List<CompletedTasks> getAll() {
        return completedTasksRepository.findAll();
    }

    @Override
    public CompletedTasks getByName(String name) {
        return null;
    }

    @Override
    public CompletedTasks getById(UUID id) {
        return completedTasksRepository.findById(id).orElse(null);
    }

    @Override
    public void save(CompletedTasks entity) {
        CompletedTasks completedTasks = completedTasksRepository.findByUserAndTask(entity.getUser(), entity.getQuestTask());
        if (completedTasks == null)
            completedTasksRepository.save(entity);
        else {
            entity = completedTasks;
            completedTasksRepository.save(entity);
        }
    }

    @Override
    public void delete(CompletedTasks entity) {
        completedTasksRepository.delete(entity);
    }

    @Override
    public CompletedTasks edit(CompletedTasks entity) {
        return null;
    }
}
