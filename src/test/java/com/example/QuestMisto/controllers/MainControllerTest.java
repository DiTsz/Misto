package com.example.QuestMisto.controllers;

import com.example.QuestMisto.services.QuestService;
import com.example.QuestMisto.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
class MainControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;
    @Mock
    private QuestService questService;

    @InjectMocks
    private MainController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    // проверка регистрации
    @Test
    void signinTrue() throws Exception {
        String name = "John";
        String surname = "Doe";
        String email = "john.doe@example.com";
        String password = "password";

        mockMvc.perform(post("/signin")
                        .param("name", name)
                        .param("surname", surname)
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        //.andExpect(redirectedUrl("/main")); -- перенапровление на главную
    }

    @Test
        // нервалиьные данные
    void signinFalse() throws Exception {
        String name = "John";
        String surname = "Doe";
        String email = "invalid_email";
        String password = "";

        mockMvc.perform(post("/signin")
                        .param("name", name)
                        .param("surname", surname)
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signin"));
    }

    // проверка на дубликат
    @Test
    void signinDuplicate() throws Exception {
        String name = "John";
        String surname = "Doe";
        String email = "john.doe@example.com";
        String password = "password";

        // первая регистрация
        mockMvc.perform(post("/signin")
                        .param("name", name)
                        .param("surname", surname)
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        //.andExpect(redirectedUrl("/main")); -- перенапровление на главную

        // повтор регистрации дубликат
        mockMvc.perform(post("/signin")
                        .param("name", name)
                        .param("surname", surname)
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signin"));
    }

    // проверка метода login
    @Test
    void loginTrue() throws Exception {
        String email = "test@example.com";
        String password = "password";

        mockMvc.perform(post("/login")
                        .param("email",email)
                        .param("password",password))
                .andExpect(status().is2xxSuccessful())
        //.andExpect(redirectedUrl("/")) или .andExpect(redirectedUrl("/main")); -- перенапровление на главную
        ;
    }

    //проверка метода login на неправельные данные
    @Test
    void loginFalse() throws Exception {
        String email = "invalid_email";
        String password = " ";

        mockMvc.perform(post("/login")
                        .param("email", email)
                        .param("password", password))
                .andExpect(status().is2xxSuccessful())
        //.andExpect(redirectedUrl("/")) или .andExpect(redirectedUrl("/main")); -- перенапровление на главную
        ;
    }
}