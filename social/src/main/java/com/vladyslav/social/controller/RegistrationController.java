package com.vladyslav.social.controller;

import com.vladyslav.social.entity.Role;
import com.vladyslav.social.entity.User;
import com.vladyslav.social.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepos userRepos;

    @GetMapping("/registration")
    public String registrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user) {

        User userFromDb = userRepos.findByUsername(user.getUsername());

        if(userFromDb != null) {
            return "redirect:/registration";
        }

        user.setActive(true);
        user.setRoleSet(Collections.singleton(Role.USER));

        userRepos.save(user);
        return "redirect:/login";
    }
}
