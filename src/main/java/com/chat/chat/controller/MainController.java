package com.chat.chat.controller;


import com.chat.chat.exception.ResourceNotFoundException;
import com.chat.chat.form.UserForm;
import com.chat.chat.model.Chat;
import com.chat.chat.model.User;
import com.chat.chat.service.MainService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    private final MainService service;

    @GetMapping
    public String index(@RequestParam(defaultValue = "1") Integer page, Principal principal, Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        if (principal != null) {
            if (service.IsThereUserByEmail(principal.getName())) {
                model.addAttribute("user", service.findUserByEmail(principal.getName()));
            }
        }
        var uri = uriBuilder.getRequestURI();
        Page<Chat> chats = service.findAllChat(PageRequest.of(page - 1, 5));
        model.addAttribute("chats", chats.getContent());
        model.addAttribute("pages", chats.getTotalPages());
        return "index";
    }

    @GetMapping("/chat/{id}")
    public String showChat(@PathVariable Long id, Principal principal, Model model) {
        if (principal != null) {
            if (service.IsThereUserByEmail(principal.getName())) {
                if (!service.isThereAnyChatById(id)) {
                    throw new ResourceNotFoundException("There is no such such with id " + id);
                }
                model.addAttribute("chatId", id);
                return "chat";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new UserForm());
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new UserForm());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid UserForm form, BindingResult bindingResult) throws BindException {

        if (bindingResult.hasFieldErrors()) {
            throw new BindException(bindingResult);
        }
        service.registerUser(form);
        return "redirect:/login";
    }

}