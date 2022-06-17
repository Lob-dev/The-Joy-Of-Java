package com.shard.demo.post.domain.service;

import com.shard.demo.global.persistence.shard.metadata.Sharding;
import com.shard.demo.post.domain.persistence.PostEntity;
import com.shard.demo.post.domain.persistence.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Sharding(target = "account", key = "#entity.accountId")
    @Transactional
    public PostEntity write(PostEntity entity) {
        return postRepository.save(entity);
    }

    @Sharding(target = "account", key = "#accountId")
    @Transactional(readOnly = true)
    public List<PostEntity> findAll(Long accountId) {
        return postRepository.findAllByAccountId(accountId);
    }
}
