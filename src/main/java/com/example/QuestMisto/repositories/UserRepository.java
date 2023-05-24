package com.example.QuestMisto.repositories;

import com.example.QuestMisto.models.User;
import com.example.QuestMisto.models.enums.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAll();
    Optional<User> findById(UUID id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
    @Modifying
    @Query("UPDATE User u SET u.authProvider = ?2 WHERE u.email = ?1")
    public void updateAuthenticationType(String email, AuthProvider authType);

}
