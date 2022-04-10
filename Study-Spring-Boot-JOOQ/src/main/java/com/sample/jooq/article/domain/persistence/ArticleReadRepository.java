package com.sample.jooq.article.domain.persistence;

import jooq.dsl.tables.Article;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ArticleReadRepository {

	private final DSLContext dsl;
	private final Article article = Article.ARTICLE;

	public Page<ArticleEntity> getPage(Pageable pageable) {
		List<ArticleEntity> articles = dsl.select()
				.from(article)
				.limit(pageable.getPageSize())
				.offset(pageable.getOffset())
				.fetchInto(ArticleEntity.class);
		return new PageImpl<>(articles, pageable, dsl.fetchCount(dsl.selectFrom(article)));
	}

	public ArticleEntity findById(Long id) {
		return dsl.select()
				.from(article)
				.where(article.ID.eq(id))
				.fetchOneInto(ArticleEntity.class);
	}
}
