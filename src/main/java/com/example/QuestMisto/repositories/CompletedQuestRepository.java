package com.example.QuestMisto.repositories;


import com.example.QuestMisto.models.entities.CompletedQuests;
import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CompletedQuestRepository extends JpaRepository<CompletedQuests, UUID> {
    List<CompletedQuests> findAll();
    Optional<CompletedQuests> findById(UUID id);
    List<CompletedQuests> findAllByUser(User user);
    CompletedQuests findByUserAndQuest(User user, Quest quest);
    int countByUser(User user);
}
