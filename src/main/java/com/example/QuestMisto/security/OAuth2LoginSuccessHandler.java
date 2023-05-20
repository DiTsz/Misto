package com.example.QuestMisto.security;

import com.example.QuestMisto.models.User;
import com.example.QuestMisto.models.enums.AuthProvider;
import com.example.QuestMisto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserService userService;
@Autowired
    public OAuth2LoginSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        String name = oAuth2User.getName();
        User user = userService.getByEmail(email);
        if (user == null) {
            userService.createUserAfterOAuth2Login(email,name, AuthProvider.GOOGLE);
        }
        else
            userService.updateUserAfterOAuth2Login(user,name,AuthProvider.GOOGLE);
        System.out.println("User`s email:" + email);
        super.onAuthenticationSuccess(request, response, chain, authentication);
    }
}
