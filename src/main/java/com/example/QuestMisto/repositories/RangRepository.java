package com.example.QuestMisto.repositories;

import com.example.QuestMisto.models.entities.Rang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface RangRepository extends JpaRepository<Rang, UUID> {
    Optional<Rang> findById(UUID id);
    List<Rang> findAll();
    Optional<Rang> findByName(String name);
}
