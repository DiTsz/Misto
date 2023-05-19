package com.example.QuestMisto.repositories;

import com.example.QuestMisto.models.CompletedQuests;
import com.example.QuestMisto.models.FeaturedQuests;
import com.example.QuestMisto.models.Quest;
import com.example.QuestMisto.models.User;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface FeaturedQuestsRepository extends JpaRepository<FeaturedQuests, UUID> {
    List<FeaturedQuests> findAll();
    Optional<FeaturedQuests> findById(UUID id);
    //@Query(value = "SELECT * FROM featured_quests WHERE featured_quests.user=:user AND featured_quests.quest=:quest ",nativeQuery = true)
    FeaturedQuests findByUserAndQuest(User user, Quest quest);
}
