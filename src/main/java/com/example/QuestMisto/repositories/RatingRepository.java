package com.example.QuestMisto.repositories;

import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.entities.Rating;
import com.example.QuestMisto.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {
    List<Rating> findAll();
    Optional<Rating> findById(UUID id);

    List<Rating> findAllByRating(Integer rating);
    Rating findByUserAndQuest(User user, Quest quest);
}
