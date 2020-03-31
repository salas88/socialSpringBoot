package com.vladyslav.social.controller;

import com.vladyslav.social.entity.Message;
import com.vladyslav.social.repository.MessageRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private MessageRepos messageRepos;

    @Autowired
    public MainController(MessageRepos messageRepos) {
        this.messageRepos = messageRepos;
    }

    @GetMapping("/main")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World")
                                                                String name, Model theModel) {
        theModel.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping
    public String mainPage(Model theModel){

        Iterable<Message> messages = messageRepos.findAll();

        theModel.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping
    public String addMessage(@RequestParam String text, String tag, Model theModel){

        Message message = new Message(text,tag);
        messageRepos.save(message);
        Iterable<Message> messages = messageRepos.findAll();

        theModel.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Model theModel) {

        Iterable messages;

        if(filter != null && !filter.isEmpty())
            messages = messageRepos.findByTag(filter);
        else
            messages = messageRepos.findAll();

        theModel.addAttribute("messages", messages);

        return "main";
    }

}
