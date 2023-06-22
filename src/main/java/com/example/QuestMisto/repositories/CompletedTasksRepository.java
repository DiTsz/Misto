package com.example.QuestMisto.repositories;

import com.example.QuestMisto.models.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompletedTasksRepository extends JpaRepository<CompletedTasks, UUID> {
    List<CompletedTasks> findAll();
    Optional<CompletedTasks> findById(UUID id);
    List<CompletedTasks> findAllByUser(User user);
    CompletedTasks findByUserAndTask(User user, QuestTask questTask);
}