package com.example.QuestMisto.Repository;

import com.example.QuestMisto.Model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestRepository extends JpaRepository<Quest, UUID> {
    @Override
    List<Quest> findAll();
    Optional<Quest> findById(UUID Id);
    Quest findQuestByName(String name);
}
