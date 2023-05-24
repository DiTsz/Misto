package com.example.QuestMisto.controllers;

import com.example.QuestMisto.models.entities.Quest;
import com.example.QuestMisto.models.entities.User;
import com.example.QuestMisto.models.enums.Role;
import com.example.QuestMisto.services.QuestService;
import com.example.QuestMisto.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class MainController {
    private final QuestService questService;
    private final UserService userService;

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

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("email") String email,
                              @RequestParam("password") String password,
                              ModelAndView modelAndView) {
        System.out.println(email);
        System.out.println(password);
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
                               @RequestParam("password") String password)
            throws Exception {

        if (new Random().nextBoolean()) {
            throw new Exception("It`s ok");
        }
        userService.save(new User(name + " " + surname, password, email, Role.USER));
        return new ModelAndView("redirect:/");
    }
}
