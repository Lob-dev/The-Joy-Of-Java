package com.sample.jooq.article.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleWriteRepository extends JpaRepository<ArticleEntity, Long> {
}
