package com.example.QuestMisto.Service.impl;


import com.example.QuestMisto.Model.Quest;
import com.example.QuestMisto.Repository.QuestRepository;
import com.example.QuestMisto.Service.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestServiceImpl implements QuestService {

    private final QuestRepository questRepository;

    @Autowired
    public QuestServiceImpl(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @Override
    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    @Override
    public Optional<Quest> getQuestById(UUID id) {
        return questRepository.findById(id);
    }

    @Override
    public Quest getQuestByName(String name) {
        return questRepository.findQuestByName(name);
    }
}
