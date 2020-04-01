package com.vladyslav.social.controller;

import com.vladyslav.social.entity.Message;
import com.vladyslav.social.entity.User;
import com.vladyslav.social.repository.MessageRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private MessageRepos messageRepos;

    @GetMapping
    public String greeting(Model theModel) {

        return "greeting";
    }

    @GetMapping("main")
    public String mainPage(Model theModel) {

        Iterable<Message> messages = messageRepos.findAll();

        theModel.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping("main")
    public String addMessage(@AuthenticationPrincipal User user,
                             @RequestParam String text,
                             String tag, Model theModel) {

        Message message = new Message(text, tag, user);
        messageRepos.save(message);
        Iterable<Message> messages = messageRepos.findAll();

        theModel.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model theModel) {

        Iterable messages;

        if (filter != null && !filter.isEmpty())
            messages = messageRepos.findByTag(filter);
        else
            messages = messageRepos.findAll();

        theModel.addAttribute("messages", messages);

        return "main";
    }

}
