package com.example.autoclickforums.service;

import com.example.autoclickforums.cache.MessageCache;
import com.example.autoclickforums.domain.Message;
import com.example.autoclickforums.exception.MessageJsonConversion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestTemplateService {
    private final MessageCache messageCache;
    private final HttpHeaders headers = new HttpHeaders();

    public RestTemplateService(MessageCache messageCache) {
        this.messageCache = messageCache;
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public void sendMessages(){
        HttpEntity<String> request = new HttpEntity<String>(convertMessagesToJson(messageCache.getLastMessages()), headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject("http://192.168.1.56:8082/message",request, List.class);
    }
    private String convertMessagesToJson(List<Message> messages){
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(messages);
        } catch (JsonProcessingException e) {
            throw new MessageJsonConversion("messages cannot be converted to json because " + e.getMessage());
        }
    }
}
