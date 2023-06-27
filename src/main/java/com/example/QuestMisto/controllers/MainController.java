package com.example.QuestMisto.controllers;

import com.example.QuestMisto.models.entities.FeaturedQuests;
import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.entities.QuestTask;
import com.example.QuestMisto.models.entities.User;
import com.example.QuestMisto.models.enums.CityName;
import com.example.QuestMisto.models.enums.QuestType;
import com.example.QuestMisto.models.enums.Role;
import com.example.QuestMisto.services.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class MainController {
    private final QuestService questService;
    private final UserService userService;
    private final QuestTaskService questTaskService;
    private final FeaturedQuestsService featuredQuestsService;
    private final CityService cityService;

    @GetMapping
    public ModelAndView getMainPage(@AuthenticationPrincipal UserDetails currentUser,
                                    ModelAndView modelAndView) {
        if (currentUser != null) {
            User user = userService.getByEmail(currentUser.getUsername());
            modelAndView.addObject("user", user);
        }
        List<Quest> quests = questService.getAll().stream().limit(5).collect(Collectors.toList());
        modelAndView.addObject("fixedHeader", true);
        modelAndView.addObject("quests", quests);
        modelAndView.setViewName("main.html");
        return modelAndView;
    }

    @GetMapping("/catalog")
    public ModelAndView getCatalogPage(@AuthenticationPrincipal UserDetails currentUser,
                                       ModelAndView modelAndView) {
        if (currentUser != null) {
            User user = userService.getByEmail(currentUser.getUsername());
            modelAndView.addObject("user", user);
        }
        List<Quest> quests = questService.getAll();
        modelAndView.addObject("city", cityService.getByName(CityName.KHARKIV));
        modelAndView.addObject("categories", QuestType.values());
        modelAndView.addObject("quests", quests);
        modelAndView.setViewName("catalog.html");
        return modelAndView;
    }

    @GetMapping("/catalog/{id}")
    public ModelAndView getQuestPage(@AuthenticationPrincipal UserDetails currentUser,
                                     @PathVariable("id") String idQuest,
                                     ModelAndView modelAndView) {
        if (currentUser != null) {
            User user = userService.getByEmail(currentUser.getUsername());
            modelAndView.addObject("user", user);
        }
        Quest quest = questService.getById(UUID.fromString(idQuest));
        modelAndView.addObject("quest", quest);
        modelAndView.setViewName("quest.html");
        return modelAndView;
    }

    @GetMapping("/catalog/categories/{category}")
    public ModelAndView getCategoryQuestsPage(@AuthenticationPrincipal UserDetails currentUser,
                                              @PathVariable("category") String category,
                                              ModelAndView modelAndView) {
        if (currentUser != null) {
            User user = userService.getByEmail(currentUser.getUsername());
            modelAndView.addObject("user", user);
        }
        List<Quest> quests = questService.getAll()
                .stream()
                .filter(q -> q.getQuestType() == QuestType.valueOf(category.toUpperCase()))
                .collect(Collectors.toList());
        modelAndView.addObject("city", cityService.getByName(CityName.KHARKIV));
        modelAndView.addObject("categories", QuestType.values());
        modelAndView.addObject("quests", quests);
        modelAndView.setViewName("catalog.html");
        return modelAndView;
    }

    @GetMapping("/catalog/start/{id}")
    public ModelAndView getStartQuestPage(@AuthenticationPrincipal UserDetails currentUser,
                                          @PathVariable("id") String idQuest,
                                          ModelAndView modelAndView) {
        if (currentUser == null) {
            return new ModelAndView("redirect:/login");
        }

        User user = userService.getByEmail(currentUser.getUsername());
        Quest quest = questService.getById(UUID.fromString(idQuest));
        QuestTask questTask = questTaskService.getAllByQuest(quest).stream().findFirst().get();
        if (featuredQuestsService.getAll().stream().anyMatch(q -> q.getUser().equals(user) && q.getQuest().equals(quest))) {
            return new ModelAndView("redirect:/account");
        }
        modelAndView.addObject("user", user);
        modelAndView.addObject("questTask", questTask);
        modelAndView.addObject("isCorrect", false);
        modelAndView.setViewName("quest-completing.html");
        return modelAndView;
    }

    @PostMapping("/catalog/start/{id}")
    public ModelAndView checkTask(@AuthenticationPrincipal UserDetails currentUser,
                                  ModelAndView modelAndView,
                                  @PathVariable("id") String idQuest,
                                  @RequestParam("order") Integer order,
                                  @RequestParam("answer") String answer) {
        if (currentUser != null) {
            User user = userService.getByEmail(currentUser.getUsername());
            modelAndView.addObject("user", user);
        }
        Quest quest = questService.getById(UUID.fromString(idQuest));
        QuestTask questTask = questTaskService.getByQuestAndOrders(quest, order);
        boolean isCorrect = questTask.getAnswers().stream().anyMatch(e -> e.equals(answer));
        modelAndView.addObject("questTask", questTask);
        modelAndView.addObject("isCorrect", isCorrect);
        modelAndView.setViewName("quest-completing.html");
        return modelAndView;
    }

    @PostMapping("/catalog/next/{id}")
    public ModelAndView getNextTask(@AuthenticationPrincipal UserDetails currentUser,
                                    ModelAndView modelAndView,
                                    @PathVariable("id") String idQuest,
                                    @RequestParam("order") Integer order) {
        Quest quest = questService.getById(UUID.fromString(idQuest));
        QuestTask questTask = questTaskService.getByQuestAndOrders(quest, order + 1);
        if (questTask == null && currentUser != null) {
            User user = userService.getByEmail(currentUser.getUsername());
            featuredQuestsService.addInFeatured(user, quest);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("done-quest.html");
            return modelAndView;
        }
        modelAndView.addObject("questTask", questTask);
        modelAndView.addObject("isCorrect", false);
        modelAndView.setViewName("quest-completing.html");
        return modelAndView;
    }

    @GetMapping("/account")
    public ModelAndView getAccountPage(@AuthenticationPrincipal UserDetails currentUser,
                                       ModelAndView modelAndView) {
        if (currentUser != null) {
            User user = userService.getByEmail(currentUser.getUsername());
            List<FeaturedQuests> quests = featuredQuestsService
                    .getAll()
                    .stream()
                    .filter(q -> q.getUser().equals(user))
                    .collect(Collectors.toList());
            UUIDGenerator generator = new UUIDGenerator();
            modelAndView.addObject("UUID", generator);
            modelAndView.addObject("quests", quests);
            modelAndView.addObject("user", user);
        } else {
            return new ModelAndView("redirect:/login");
        }
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
        try{
            userService.save(new User(name + " " + surname, password, email, Role.USER));
        }catch (Exception ex){
            ex.printStackTrace();
            return new ModelAndView("redirect:/signin");
        }
        return new ModelAndView("redirect:/");
    }
}
