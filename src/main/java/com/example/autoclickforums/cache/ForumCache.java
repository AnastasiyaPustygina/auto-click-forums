package com.example.autoclickforums.cache;

import com.example.autoclickforums.domain.Forum;
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
public class ForumCache {
    public static List<Forum> forums = new ArrayList<>();

}
