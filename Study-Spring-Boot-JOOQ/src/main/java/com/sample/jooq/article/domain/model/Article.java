package com.sample.jooq.article.domain.model;

import com.sample.jooq.article.domain.persistence.ArticleEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Article {

	private final Long id;
	private final String title;
	private final String description;
	private final Long authorId;

	@Builder
	public Article(Long id, String title, String description, Long authorId) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.authorId = authorId;
	}

	public ArticleEntity toEntity() {
		return ArticleEntity.builder()
				.id(id)
				.title(title)
				.description(description)
				.authorId(authorId)
				.build();
	}
}
