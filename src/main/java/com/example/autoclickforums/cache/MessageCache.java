package com.example.autoclickforums.cache;

import com.example.autoclickforums.domain.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Component
public class MessageCache {
    private final List<Message> lastMessages = new ArrayList<>();
    public void addToList(Message message){
        lastMessages.add(message);
    }
    public void clearList(){
        lastMessages.clear();
    }
}

