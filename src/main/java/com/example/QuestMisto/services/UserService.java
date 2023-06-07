package com.example.QuestMisto.services;

import com.example.QuestMisto.interfaces.RepositoryService;
import com.example.QuestMisto.models.entities.User;
import com.example.QuestMisto.models.enums.AuthProvider;
import com.example.QuestMisto.models.enums.Role;
import com.example.QuestMisto.models.enums.Status;
import com.example.QuestMisto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements RepositoryService<User> {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final UserAvatarService userAvatarService;
    private final RangService rangService;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserDetailsService userDetailsService,
                       AuthenticationManager authenticationManager,
                       UserAvatarService userAvatarService,
                       RangService rangService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.userAvatarService = userAvatarService;
        this.rangService = rangService;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByName(String name) {
        return userRepository.findByUsername(name).orElse(null);
    }

    @Override
    public User getById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User entity) {
        User user = userRepository.findByUsername(entity.getUsername()).orElse(null);
        if (user == null) {
            entity.setUserAvatar(userAvatarService.getByName("Default avatar"));
            entity.setRang(rangService.getByName("Newbie"));
            entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
            userRepository.save(entity);
        }
        userRepository.save(entity);

    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    public User edit(User entity) {
        userRepository.findByUsername(entity.getUsername()).ifPresent(user -> userRepository.save(entity));
        return entity;
    }

    public User editWithPassword(User entity, String oldPassword, String newPassword) {
        User user = new User();
        if (oldPassword.equals(entity.getPassword()))
            user.setPassword(newPassword);

        user.setId(entity.getId());
        user.setUsername(entity.getUsername());
        user.setEmail(entity.getEmail());
        user.setUserAvatar(entity.getUserAvatar());
        user.setStatus(entity.getStatus());
        user.setRole(entity.getRole());
        user.setRatings(entity.getRatings());
        userRepository.save(user);
        return user;
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void login(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(token);

        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
        } else {
            System.out.println("Error with authentication" + email);
        }
    }

    /* public void createUserAfterOAuth2Login(String email, String name, AuthProvider authProvider) {
         User user = new User();
         user.setUsername(name);
         user.setEmail(email);
         user.setAuthProvider(authProvider);
         user.setRole(Role.USER);
         userRepository.save(user);
     }

     public void updateUserAfterOAuth2Login(User user, String name, AuthProvider authProvider) {
         user.setUsername(name);
         user.setAuthProvider(authProvider);
         user.setRole(Role.USER);
         userRepository.save(user);
     }*/
    public void processOAuthPostLogin(String username, String email, String oauth2ClientName) {
        User existUser = userRepository.findByUsername(username).orElse(null);

        if (existUser == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setRole(Role.USER);
            newUser.setAuthProvider(AuthProvider.GOOGLE);
            newUser.setStatus(Status.ACTIVE);
            newUser.setUserAvatar(userAvatarService.getByName("default avatar"));
            newUser.setRang(rangService.getByName("Newbie"));
            userRepository.save(newUser);
        }
        AuthProvider authType = AuthProvider.valueOf(oauth2ClientName.toUpperCase());
        userRepository.updateAuthenticationType(email, authType);
    }

    public void updateAuthenticationType(String email, String oauth2ClientName) {
        AuthProvider authType = AuthProvider.valueOf(oauth2ClientName.toUpperCase());
        userRepository.updateAuthenticationType(email, authType);
    }
}
