package com.shard.demo.post.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findAllByAccountId(Long accountId);
}
