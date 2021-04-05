package com.chat.chat.Utils;

import com.chat.chat.model.Chat;
import com.chat.chat.model.Message;
import com.chat.chat.model.User;
import com.chat.chat.repository.ChatRepository;
import com.chat.chat.repository.MessageRepository;
import com.chat.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@RequiredArgsConstructor
public class PreloadData {
    private final PasswordEncoder passwordEncoder;


    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   ChatRepository chatRepository,
                                   MessageRepository messageRepository) {
        return (args) -> {
            messageRepository.deleteAll();
            chatRepository.deleteAll();
            userRepository.deleteAll();

            List<User> users = makeUsers(userRepository);
            userRepository.saveAll(users);

            List<Chat> chats = makeChats();
            chatRepository.saveAll(chats);

            List<Message> messages = makeMessage(users, chats);
            messageRepository.saveAll(messages);


        };
    }

    private List<Message> makeMessage(List<User> users, List<Chat> chats) {
        List<Message> messages = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 0; i < 40; i++) {
            messages.add(Message.builder()
                    .text("Message#" + i)
                    .user(users.get(rnd.nextInt(4) + 1))
                    .chat(chats.get(rnd.nextInt(14) + 1))
                    .date(LocalDateTime.now())
                    .build());
        }

        return messages;

    }

    private List<Chat> makeChats() {
        Random rnd = new Random();
        List<Chat> chats = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            chats.add(Chat.builder()
                    .nameOfChat("ChatName" + i)
                    .build());
        }
        return chats;
    }

    private List<User> makeUsers(UserRepository userRepository) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            users.add(User.builder()
                    .email("test@gmail" + i)
                    .password(passwordEncoder.encode("test123123"))
                    .build());
        }
        return users;
    }
}
