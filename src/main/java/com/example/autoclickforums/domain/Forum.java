package com.example.autoclickforums.domain;

import lombok.*;

@Data
@AllArgsConstructor
public class Forum {
    private final String name;
    private long timeDelaySeconds;
    private String url;
    private User user;
    private final IdElements idElements;

}
