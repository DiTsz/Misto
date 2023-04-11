package com.example.QuestMisto.Service;
import com.example.QuestMisto.Model.Quest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestService {
    List<Quest> getAllQuests();

    Optional<Quest> getQuestById(UUID id);

    Quest getQuestByName(String name);
}
