package com.example.autoclickforums.service;

import com.example.autoclickforums.cache.ForumCache;
import com.example.autoclickforums.cache.MessageCache;
import com.example.autoclickforums.domain.Forum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ExecutionService {
    private final List<Forum> forums = ForumCache.forums;
    private final AutoClickService autoClickService;
    private final AuthorizeService authorizeService;
    private final MessageCache messageCache;
    private final RestTemplateService restTemplateService;

    public void  start() {
        forums.forEach(f -> Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                performClicks();
            }
        }, 0, f.getTimeDelaySeconds(), TimeUnit.SECONDS));
    }
    private void performClicks(){
        messageCache.clearList();

        forums.forEach(f -> {
            authorizeService.authorize(f);
            try {
                wait(5 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            autoClickService.clickButton(f.getUrl(), f.getIdElements().getIdMainButton());
        });
        restTemplateService.sendMessages();
    }

}
