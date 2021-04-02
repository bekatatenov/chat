package com.chat.chat.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String session;

    private String nameOfChat;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    private List<Message> messages;
}
