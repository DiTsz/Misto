package com.example.QuestMisto.interfaces;

import java.util.List;
import java.util.UUID;

public interface RepositoryService<T> {
    List<T> getAll();
    T getByName(String name);
    T getById(UUID id);
    void save(T entity);
    void delete(T entity);
}
