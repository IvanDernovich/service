package com.example.service;

import com.example.service.domain.Message;
import com.example.service.repos.MessagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController<messages> {
    @Autowired
    private MessagesRepo messagesRepo;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World")
                    String name, Map<String, Object> model)
    {
        model.put("name", name);
        return "greeting";
    }
    @GetMapping
    public String main(Map<String,Object>model){
        Iterable<Message> messages = messagesRepo.findAll();
        model.put("messages",messages);
        return "main";
    }
    @PostMapping
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model){
        Message message = new Message(text,tag);
        messagesRepo.save(message);
            Iterable<Message> messages = messagesRepo.findAll();
        model.put("messages",messages);
            return "main";
    }
    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messagesRepo.findByTag(filter);
        } else {
            messages = messagesRepo.findAll();
        }
        model.put("messages",messages);
        return "main";
    }
}
