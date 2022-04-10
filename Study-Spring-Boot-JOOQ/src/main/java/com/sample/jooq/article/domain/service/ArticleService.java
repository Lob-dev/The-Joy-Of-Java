package com.sample.jooq.article.domain.service;

import com.sample.jooq.article.domain.model.Article;
import com.sample.jooq.article.domain.persistence.ArticleEntity;
import com.sample.jooq.article.domain.persistence.ArticleReadRepository;
import com.sample.jooq.article.domain.persistence.ArticleWriteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleWriteRepository articleWriteRepository;
    private final ArticleReadRepository articleReadRepository;

    public Page<ArticleEntity> gets(Pageable pageable) {
        return articleReadRepository.getPage(pageable);
    }

    public ArticleEntity getOne(Long articleId) {
        final ArticleEntity targetArticle = articleReadRepository.findById(articleId);
        Assert.notNull(targetArticle, "article not found");
        return targetArticle;
    }

    public ArticleEntity addArticle(Article article) {
        return articleWriteRepository.save(article.toEntity());
    }

    public ArticleEntity updateArticle(Article article, Long authorId) {
        final ArticleEntity targetArticle = getOne(article.getId());
        if (targetArticle.isOwner(authorId)) {
            targetArticle.updateStates(article.getTitle(), article.getDescription());
            return targetArticle;
        }
        log.info("This account is not owner || targetId = {} || author id = {} || date = {}", article.getId(), authorId, LocalDateTime.now());
        throw new RuntimeException("This account is not owner");
    }

    public void deleteArticle(Long articleId, Long authorId) {
        final ArticleEntity targetArticle = getOne(articleId);
        if (targetArticle.isOwner(authorId)) {
            articleWriteRepository.delete(targetArticle);
            return;
        }
        log.info("This account is not owner || targetId = {} || author id = {} || date = {}", articleId, authorId, LocalDateTime.now());
        throw new RuntimeException("This account is not owner");
    }
}
