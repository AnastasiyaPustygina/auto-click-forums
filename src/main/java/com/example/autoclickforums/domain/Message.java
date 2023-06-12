package com.example.autoclickforums.domain;

import lombok.*;

import java.net.URL;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Message {
    private final String forumUrl;
    private String text;

    private Status status;
}
