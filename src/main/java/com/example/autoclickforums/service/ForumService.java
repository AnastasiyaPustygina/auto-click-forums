package com.example.autoclickforums.service;

import com.example.autoclickforums.dao.ForumDao;
import com.example.autoclickforums.domain.Forum;
import com.example.autoclickforums.domain.User;
import com.example.autoclickforums.exception.ForumNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumService {
    private final ForumDao dao;

    public void updateUrl(String name, String url){
        List<Forum> forums = dao.getForums();
        forums.stream().filter(f -> f.getName().equals(name)).findFirst().orElseThrow(
                () -> new ForumNotFoundException(name)).setUrl(url);
        dao.updateForums(forums);
    }

    public void updateTimeDelaySeconds(String name, long timeDelaySeconds){
        List<Forum> forums = dao.getForums();
        forums.stream().filter(f -> f.getName().equals(name)).findFirst().orElseThrow(
                () -> new ForumNotFoundException(name)).setTimeDelaySeconds(timeDelaySeconds);
        dao.updateForums(forums);
    }

    public void updateLogin(String name, String login){
        List<Forum> forums = dao.getForums();
        Forum forum = forums.stream().filter(f -> f.getName().equals(name)).findFirst().orElseThrow(
                () -> new ForumNotFoundException(name));
        User user = forum.getUser();
        user.setLogin(login);
        forums.stream().filter(f -> f.getName().equals(name)).findFirst().orElseThrow(
                () -> new ForumNotFoundException(name)).setUser(user);
        dao.updateForums(forums);
    }
    public void updatePassword(String name, String password){
        List<Forum> forums = dao.getForums();
        Forum forum = forums.stream().filter(f -> f.getName().equals(name)).findFirst().orElseThrow(
                () -> new ForumNotFoundException(name));
        User user = forum.getUser();
        user.setLogin(password);
        forums.stream().filter(f -> f.getName().equals(name)).findFirst().orElseThrow(
                () -> new ForumNotFoundException(name)).setUser(user);
        dao.updateForums(forums);
    }
}