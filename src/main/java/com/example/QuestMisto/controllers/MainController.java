package com.example.QuestMisto.controllers;

import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.entities.QuestTask;
import com.example.QuestMisto.models.entities.User;
import com.example.QuestMisto.models.enums.Role;
import com.example.QuestMisto.security.UserDetailsImpl;
import com.example.QuestMisto.services.QuestService;
import com.example.QuestMisto.services.QuestTaskService;
import com.example.QuestMisto.services.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.Authenticator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class MainController {
    private final QuestService questService;
    private final UserService userService;
    private final QuestTaskService questTaskService;
    private final Authenticator authenticator;

    @GetMapping
    public ModelAndView getMainPage(ModelAndView modelAndView) {
        List<Quest> quests = questService.getAll().stream().limit(5).collect(Collectors.toList());
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

    @GetMapping("/catalog/{id}")
    public ModelAndView getQuestPage(@PathVariable("id") String idQuest, ModelAndView modelAndView) {
        Quest quest = questService.getById(UUID.fromString(idQuest));
        modelAndView.addObject("quest", quest);
        modelAndView.setViewName("quest.html");
        return modelAndView;
    }

    @GetMapping("/catalog/start/{id}")
    public ModelAndView getStartQuestPage(@PathVariable("id") String idQuest, ModelAndView modelAndView) {
        Quest quest = questService.getById(UUID.fromString(idQuest));
        QuestTask questTask = questTaskService.getAllByQuest(quest).stream().findFirst().get();
        modelAndView.addObject("questTask", questTask);
        modelAndView.addObject("isCorrect", false);
        modelAndView.setViewName("quest-completing.html");
        return modelAndView;
    }

    @PostMapping("/catalog/start/{id}")
    public ModelAndView checkTask(ModelAndView modelAndView, @PathVariable("id") String idQuest, @RequestParam("order") Integer order, @RequestParam("answer") String answer) {
        Quest quest = questService.getById(UUID.fromString(idQuest));
        QuestTask questTask = questTaskService.getByQuestAndOrders(quest, order);
        boolean isCorrect = questTask.getAnswers().stream().anyMatch(e -> e.equals(answer));
        modelAndView.addObject("questTask", questTask);
        modelAndView.addObject("isCorrect", isCorrect);
        modelAndView.setViewName("quest-completing.html");
        return modelAndView;
    }

    @PostMapping("/catalog/next/{id}")
    public ModelAndView getNextTask(ModelAndView modelAndView, @PathVariable("id") String idQuest, @RequestParam("order") Integer order) {
        Quest quest = questService.getById(UUID.fromString(idQuest));
        QuestTask questTask = questTaskService.getByQuestAndOrders(quest, order + 1);
        if (questTask == null) {
            return new ModelAndView("redirect:/");
        }
        modelAndView.addObject("questTask", questTask);
        modelAndView.addObject("isCorrect", false);
        modelAndView.setViewName("quest-completing.html");
        return modelAndView;
    }

    @GetMapping("/account")
    public ModelAndView getAccountPage(@AuthenticationPrincipal UserDetails currentUser, ModelAndView modelAndView) {
        modelAndView.addObject("user", currentUser);
        modelAndView.setViewName("account.html");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              ModelAndView modelAndView) {
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @GetMapping("/signin")
    public ModelAndView signin(ModelAndView modelAndView) {
        modelAndView.setViewName("signin.html");
        return modelAndView;
    }

    @PostMapping("/signin")
    public ModelAndView signin(@RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {
        userService.save(new User(name + " " + surname, password, email, Role.USER));
        return new ModelAndView("redirect:/");
    }
}
