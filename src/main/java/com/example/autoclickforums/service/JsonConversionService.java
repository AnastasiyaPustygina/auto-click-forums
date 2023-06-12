package com.example.autoclickforums.service;

import com.example.autoclickforums.domain.Forum;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class JsonConversionService {
    public static List<Forum> convertJsonToForumList(String jsonString){
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonString, new TypeReference<List<Forum>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertToJson(List<Forum> forums){
        return new Gson().toJson(forums);
    }

}
