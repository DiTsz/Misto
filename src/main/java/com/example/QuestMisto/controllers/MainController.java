package com.example.QuestMisto.controllers;

import com.example.QuestMisto.models.City;
import com.example.QuestMisto.models.Quest;
import com.example.QuestMisto.models.enums.QuestType;
import com.example.QuestMisto.models.enums.TypeReward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.UUID;

@RestController
public class MainController {
    @Autowired
    private MainController() {
    }

    @GetMapping
    public ModelAndView getMainPage(ModelAndView modelAndView) {
        Quest quest1 = new Quest();
        quest1.setId(UUID.randomUUID());
        quest1.setName("Cymska quest");
        quest1.setDescription("Quest description");
        quest1.setQuestType(QuestType.GASTRO);
        quest1.setCity(new City());
        quest1.setDifficulty(3);
        quest1.setRating(5);
        quest1.setTypeReward(TypeReward.PROMOCODE);
        quest1.setReward("qeqf34645yeg");
        quest1.setNumOfSteps(12);
        quest1.setDistance(1200f);
        quest1.setDuration("90-120");
        quest1.setPicture_url("https://st4.depositphotos.com/4105125/22151/i/450/" +
                "depositphotos_221510876-stock-photo-aerial-view-arrow-place-confluence.jpg");

        ArrayList<Quest> quests = new ArrayList<>();
        quests.add(quest1);
        quests.add(quest1);

        modelAndView.addObject("quests", quests);
        modelAndView.setViewName("Main.html");
        return modelAndView;
    }
}
