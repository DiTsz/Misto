package com.example.QuestMisto.services;

import com.example.QuestMisto.interfaces.RepositoryService;
import com.example.QuestMisto.models.Quest;
import com.example.QuestMisto.repositories.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class QuestService implements RepositoryService {

    private final QuestRepository questRepository;

    @Autowired
    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @Override
    public List<Quest> getAll() {
        return questRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
    }

    @Override
    public Quest getById(UUID id) {
        return questRepository.findById(id).orElse(null);
    }

    @Override
    public Quest getByName(String name) {
        return questRepository.findByName(name);
    }

    @Override
    public void save(Object entity) {
        questRepository.save((Quest) entity);
    }

    @Override
    public void delete(Object entity) {
        questRepository.delete((Quest) entity);

    }
}