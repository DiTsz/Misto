package com.example.QuestMisto.services;

import com.example.QuestMisto.exceptions.DuplicateEntityException;
import com.example.QuestMisto.interfaces.RepositoryService;
import com.example.QuestMisto.models.entities.*;
import com.example.QuestMisto.repositories.CompletedQuestRepository;
import com.example.QuestMisto.repositories.CompletedTasksRepository;
import com.example.QuestMisto.repositories.QuestTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class QuestTaskService implements RepositoryService<QuestTask> {

    private final QuestTaskRepository questTaskRepository;
    private final CompletedQuestRepository completedQuestRepository;
    private final CompletedTasksRepository completedTasksRepository;

    @Autowired
    public QuestTaskService(QuestTaskRepository questTaskRepository,
                            CompletedQuestRepository completedQuestRepository,
                            CompletedTasksRepository completedTasksRepository) {
        this.questTaskRepository = questTaskRepository;
        this.completedQuestRepository = completedQuestRepository;
        this.completedTasksRepository = completedTasksRepository;
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
        try {
            for (QuestTask qt : questTaskRepository
                    .findAllByQuestOrderByOrders(entity.getQuest())) {
                if (qt.getOrders() == entity.getOrders()) {
                    throw new DuplicateEntityException("Duplicate unique field");
                }
            }
            questTaskRepository.save(entity);
        } catch (DuplicateEntityException ex) {
            System.out.println("Duplicate field 'orders' with value: " + entity.getOrders());
        }
    }

    @Override
    public void delete(QuestTask entity) {
        questTaskRepository.delete(entity);
    }

    @Override
    public QuestTask edit(QuestTask entity) {
        return null;
    }

    public List<QuestTask> getAllByQuest(Quest quest) {
        return questTaskRepository.findAllByQuestOrderByOrders(quest);
    }

    public QuestTask getByQuestAndOrders(Quest quest, int orders) {
        return questTaskRepository.findByQuestAndOrders(quest, orders);
    }

    public boolean hasNextTask(QuestTask currentTask) {
        return questTaskRepository.findByQuestAndOrders(currentTask.getQuest(), currentTask.getOrders() + 1) != null;
    }

    @Transactional
    public QuestTask getNextQuestTask(QuestTask questTask, String answer, User user) {
        QuestTask currentTask = questTaskRepository
                .findByQuestAndOrders(questTask.getQuest(), questTask.getOrders());
        if (isAnswerRight(currentTask, answer)) {
            CompletedTasks completedTasks = new CompletedTasks(user, currentTask);
            completedTasksRepository.save(completedTasks);
            QuestTask nextTask = questTaskRepository
                    .findByQuestAndOrders(currentTask.getQuest(), currentTask.getOrders() + 1);
            if (nextTask == null) {
                CompletedQuests completedQuest = new CompletedQuests(user, currentTask.getQuest());
                completedQuestRepository.save(completedQuest);
                return null;
            }
            return nextTask;
        }
        return currentTask;
    }

    @Transactional
    public boolean isAnswerRight(QuestTask task, String answer) {
        QuestTask task1 = questTaskRepository.findByQuestAndOrders(task.getQuest(), task.getOrders());
        for (String s : task1.getAnswers()) {
            if (answer.equals(s)) {
                return true;
            }
        }
        return false;
    }
}

