package com.chat.chat.service;

import com.chat.chat.exception.ResourceNotFoundException;
import com.chat.chat.exception.UserAlreadyRegisteredException;
import com.chat.chat.form.UserForm;
import com.chat.chat.model.Chat;
import com.chat.chat.model.Message;
import com.chat.chat.model.User;
import com.chat.chat.repository.ChatRepository;
import com.chat.chat.repository.MessageRepository;
import com.chat.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class MainService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean IsThereUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Page<Chat> findAllChat(Pageable pageable) {
        return chatRepository.findAll(pageable);
    }

    public Chat findChatById(Long id) {
        return chatRepository.findById(id).get();
    }

    public boolean isThereAnyChatById(Long id) {
        return chatRepository.existsById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User registerUser(UserForm form) {
        if (userRepository.existsByEmail(form.getEmail())) {
            throw new ResourceNotFoundException("User Already Registered");
        }
        User user = User.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .build();
        userRepository.save(user);
        return user;
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findAllMessages() {
        return messageRepository.findAll();
    }
}
