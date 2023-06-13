package com.example.autoclickforums.controller;

import com.example.autoclickforums.domain.Forum;
import com.example.autoclickforums.service.ExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ExecutionController {

    private final ExecutionService executionService;

    @PostMapping(path = "/start")
    @ResponseStatus(value = HttpStatus.OK)
    public void start() {
        executionService.start();
    }

}
