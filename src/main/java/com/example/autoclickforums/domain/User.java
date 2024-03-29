package com.example.autoclickforums.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String login;
    private String secretWord;

    private String password;
}
