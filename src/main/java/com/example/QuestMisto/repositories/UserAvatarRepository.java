package com.example.QuestMisto.repositories;

import com.example.QuestMisto.models.UserAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserAvatarRepository extends JpaRepository<UserAvatar, UUID> {
    List<UserAvatar> findAll();
    Optional<UserAvatar> findById(UUID id);

    Optional<UserAvatar> findByName(String name);
}
