package com.example.QuestMisto.repositories;

import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.entities.QuestTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface QuestTaskRepository extends JpaRepository<QuestTask, UUID> {
    Optional<QuestTask> findById(UUID id);
    List<QuestTask> findAllByQuestOrderByOrders(Quest quest);
    List<QuestTask> findAll();
    QuestTask findByQuestAndOrders(Quest quest,int orders);
}
