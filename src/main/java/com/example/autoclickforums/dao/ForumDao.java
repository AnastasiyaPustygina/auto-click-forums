package com.example.autoclickforums.dao;

import com.example.autoclickforums.cache.ForumCache;
import com.example.autoclickforums.domain.Forum;
import com.example.autoclickforums.service.JsonConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ForumDao {

    private static final String PATH_TO_FILE = "data/forums.txt";
    public List<Forum> getForums(){
        if(ForumCache.forums.isEmpty()){
            try {
                String jsonString = Files.readString(Path.of(URI.create(PATH_TO_FILE)));
                ForumCache.forums = JsonConversionService.convertJsonToForumList(jsonString);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ForumCache.forums;
    }

    public void updateForums(List<Forum> forums){
        String jsonString = JsonConversionService.convertToJson(forums);
        try {
            PrintWriter printWriter = new PrintWriter(PATH_TO_FILE);
            printWriter.print(jsonString);
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
