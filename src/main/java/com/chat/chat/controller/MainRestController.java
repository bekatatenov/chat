package com.chat.chat.controller;

import com.chat.chat.model.Chat;
import com.chat.chat.model.Message;
import com.chat.chat.model.User;
import com.chat.chat.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class MainRestController {

    private final MainService service;

    @PostMapping("/add")
    public void addMessage(@RequestParam("text") String text, @RequestParam("id") Long id, Principal principal) {
        Chat chatById = service.findChatById(id);
        User user = service.findUserByEmail(principal.getName());
        Message message = Message.builder()
                .chat(chatById)
                .date(LocalDateTime.now())
                .user(user)
                .text(text)
                .build();
        service.saveMessage(message);
    }


    @GetMapping("/get")
    public List<Message> getMessages() {
        List<Message> allMessages = service.findAllMessages();
        return allMessages;
    }
}
