package com.example.autoclickforums.controller;

import com.example.autoclickforums.service.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ForumController {

    public ForumService forumService;

    @PatchMapping(path = "/forum/url/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateUrl(@PathVariable(name = "name") String name, @RequestParam(name = "url") String url) {
        forumService.updateUrl(name, url);
    }

    @PatchMapping(path = "/forum/time_delay_seconds/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateTimeDelaySeconds(@PathVariable(name = "name") String name,
                                       @RequestParam(name = "time_delay_seconds") long time_delay_seconds) {
        forumService.updateTimeDelaySeconds(name, time_delay_seconds);
    }

    @PatchMapping(path = "/user/login/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updateLogin(@PathVariable(name = "name") String name, @RequestParam(name = "login") String login) {
        forumService.updateLogin(name, login);
    }

    @PatchMapping(path = "/user/password/{name}")
    @ResponseStatus(value = HttpStatus.OK)
    public void updatePassword(@PathVariable(name = "name") String name, @RequestParam(name = "password") String password) {
        forumService.updatePassword(name, password);
    }

}
