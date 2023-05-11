package com.example.QuestMisto.services;

import com.example.QuestMisto.interfaces.RepositoryService;
import com.example.QuestMisto.models.UserAvatar;
import com.example.QuestMisto.repositories.UserAvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserAvatarService implements RepositoryService<UserAvatar> {
    private final UserAvatarRepository userAvatarRepository;

    @Autowired
    public UserAvatarService(UserAvatarRepository userAvatarRepository) {
        this.userAvatarRepository = userAvatarRepository;
    }

    @Override
    public List<UserAvatar> getAll() {
        return userAvatarRepository.findAll();
    }

    @Override
    public UserAvatar getByName(String name) {
        return userAvatarRepository.findByName(name).orElse(null);
    }

    @Override
    public UserAvatar getById(UUID id) {
        return userAvatarRepository.findById(id).orElse(null);
    }

    @Override
    public void save(UserAvatar entity) {
        userAvatarRepository.save(entity);
    }

    @Override
    public void delete(UserAvatar entity) {
        userAvatarRepository.delete(entity);
    }

    @Override
    public UserAvatar edit(UserAvatar entity) {
        return null;
    }

}
