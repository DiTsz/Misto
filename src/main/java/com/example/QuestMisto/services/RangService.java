package com.example.QuestMisto.services;

import com.example.QuestMisto.interfaces.RepositoryService;
import com.example.QuestMisto.models.entities.Rang;
import com.example.QuestMisto.repositories.RangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RangService implements RepositoryService<Rang> {
    private final RangRepository repository;

    @Autowired
    public RangService(RangRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Rang> getAll() {
        return repository.findAll();
    }

    @Override
    public Rang getByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    @Override
    public Rang getById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(Rang entity) {
        repository.save(entity);
    }

    @Override
    public void delete(Rang entity) {
        repository.delete(entity);
    }

    @Override
    public Rang edit(Rang entity) {
        return null;
    }
}
