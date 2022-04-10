package com.sample.jooq;

import com.sample.jooq.account.domain.model.Account;
import com.sample.jooq.account.domain.persistence.AccountEntity;
import com.sample.jooq.account.domain.service.AccountService;
import com.sample.jooq.article.domain.model.Article;
import com.sample.jooq.article.domain.persistence.ArticleEntity;
import com.sample.jooq.article.domain.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Transactional
@SpringBootTest
class JooqApplicationTests {

	@Autowired
	private AccountService accountService;

	@Autowired
	private ArticleService articleService;

	@Test
	void account_save() {

		// Arrange
		final Account newAccount = Account.builder()
				.email("helloWorld@test.com")
				.username("name")
				.password("password")
				.role("USER")
				.build();

		// Act
		AccountEntity savedAccount = accountService.save(newAccount);

		// Assert
		assertNotNull(savedAccount);
	}


	@Test
	void account_findOne() {

		// Arrange
		final Account newAccount = Account.builder()
				.email("helloWorld@test.com")
				.username("name")
				.password("password")
				.role("USER")
				.build();

		accountService.save(newAccount);

		// Act
		AccountEntity targetAccount = accountService.findOne("name");

		// Assert
		assertNotNull(targetAccount);
	}


	@Test
	void article_gets() {

		// Arrange
		final Account newAccount = Account.builder()
				.email("helloWorld@test.com")
				.username("name")
				.password("password")
				.role("USER")
				.build();

		AccountEntity author = accountService.save(newAccount);

		for (int i = 0; i < 10; i++) {
			Article article = Article.builder()
					.title("title" + i)
					.description("description" + i)
					.authorId(author.getId())
					.build();

			articleService.addArticle(article);
		}

		// Act
		Page<ArticleEntity> page = articleService.gets(PageRequest.of(0, 10));

		// Assert
		assertFalse(page.isEmpty());
	}


	@Test
	void article_addArticle() {

		// Arrange
		final Account newAccount = Account.builder()
				.email("helloWorld@test.com")
				.username("name")
				.password("password")
				.role("USER")
				.build();

		AccountEntity author = accountService.save(newAccount);

		Article article = Article.builder()
				.title("title")
				.description("description")
				.authorId(author.getId())
				.build();

		ArticleEntity savedArticle = articleService.addArticle(article);

		// Act
		ArticleEntity targetArticle = articleService.getOne(savedArticle.getId());

		// Assert
		assertNotNull(targetArticle);
	}


	@Test
	void article_updateArticle() {

		// Arrange
		final Account newAccount = Account.builder()
				.email("helloWorld@test.com")
				.username("name")
				.password("password")
				.role("USER")
				.build();

		AccountEntity author = accountService.save(newAccount);
		Long authorId = author.getId();

		Article article = Article.builder()
				.title("title")
				.description("description")
				.authorId(authorId)
				.build();

		ArticleEntity savedArticle = articleService.addArticle(article);

		final Article updateArticle = Article.builder()
				.id(savedArticle.getId())
				.title("update title")
				.description("update description")
				.authorId(authorId)
				.build();

		// Act
		ArticleEntity targetArticle = articleService.updateArticle(updateArticle, authorId);

		// Assert
		assertNotNull(targetArticle);
	}


	@Test
	void article_deleteArticle() {

		// Arrange
		final Account newAccount = Account.builder()
				.email("helloWorld@test.com")
				.username("name")
				.password("password")
				.role("USER")
				.build();

		AccountEntity author = accountService.save(newAccount);
		Long authorId = author.getId();

		Article article = Article.builder()
				.title("title")
				.description("description")
				.authorId(authorId)
				.build();

		ArticleEntity savedArticle = articleService.addArticle(article);

		// Act & Assert
		articleService.deleteArticle(savedArticle.getId(), authorId);
	}
}
