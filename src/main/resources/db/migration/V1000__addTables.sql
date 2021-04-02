create table chats
(
    id           bigint auto_increment
        primary key,
    name_of_chat varchar(255) null,
    session      varchar(255) null
);

create table messages
(
    id      bigint auto_increment
        primary key,
    date    date         null,
    text    varchar(255) null,
    chat_id bigint       null,
    user_id bigint       null,
    constraint FK64w44ngcpqp99ptcb9werdfmb
        foreign key (chat_id) references chats (id)
);

create table users
(
    id         bigint auto_increment
        primary key,
    email      varchar(255) null,
    password   varchar(255) null,
    chat_id    bigint       null,
    message_id bigint       null,
    constraint FK7fq7mrgspmqpf2r2yaqlck5k4
        foreign key (chat_id) references chats (id),
    constraint FKkuykkp5261g2w9m1elx2s50tu
        foreign key (message_id) references messages (id)
);

alter table messages
    add constraint FKpsmh6clh3csorw43eaodlqvkn
        foreign key (user_id) references users (id);

