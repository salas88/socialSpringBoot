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
    public String registrationPage(User user) {

        User userFromDb = userRepos.findByUsername(user.getUserName());

        if(userFromDb != null) {
            return "redirect:/registration";
        }

        user.setRoleSet(Collections.singleton(Role.USER));
        user.setActive(true);
        userRepos.save(user);

        return "redirect:/login";
    }
}
