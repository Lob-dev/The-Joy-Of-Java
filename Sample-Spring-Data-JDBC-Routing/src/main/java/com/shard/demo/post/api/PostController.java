package com.shard.demo.post.api;

import com.shard.demo.post.domain.persistence.PostEntity;
import com.shard.demo.post.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public PostEntity write(@RequestBody PostEntity postEntity) {
        return postService.write(postEntity);
    }

    @GetMapping("/{accountId}")
    public List<PostEntity> findOne(@PathVariable Long accountId) {
        return postService.findAll(accountId);
    }
}
