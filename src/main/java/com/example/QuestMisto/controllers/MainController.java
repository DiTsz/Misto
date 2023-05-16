package com.example.QuestMisto.controllers;

import com.example.QuestMisto.models.Quest;
import com.example.QuestMisto.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {
    private final QuestService questService;

    @Autowired
    private MainController(QuestService questService) {
        this.questService = questService;
    }

    @GetMapping
    public ModelAndView getMainPage(ModelAndView modelAndView) {
        List<Quest> quests = questService.getAll().stream().limit(5).collect(Collectors.toCollection(ArrayList::new));
        modelAndView.addObject("fixedHeader", true);
        modelAndView.addObject("quests", quests);
        modelAndView.setViewName("main.html");
        return modelAndView;
    }

    @GetMapping("/catalog")
    public ModelAndView getCatalogPage(ModelAndView modelAndView) {
        List<Quest> quests = questService.getAll();
        modelAndView.addObject("quests", quests);
        modelAndView.setViewName("catalog.html");
        return modelAndView;
    }
}
