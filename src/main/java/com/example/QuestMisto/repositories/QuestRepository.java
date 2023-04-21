package com.example.QuestMisto.repositories;

import com.example.QuestMisto.models.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestRepository extends JpaRepository<Quest, UUID> {
    List<Quest> findAll();
    Optional<Quest> findById(UUID Id);
    Quest findByName(String name);


}
