package com.sample.jooq.account.domain.model;

import com.sample.jooq.account.domain.persistence.AccountEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Account {

	private final Long id;
	private final String password;
	private final String username;
	private final String email;
	private final String role;

	@Builder
	public Account(Long id, String username, String password, String email, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}

	public AccountEntity toEntity() {
		return AccountEntity.builder()
				.id(id)
				.username(username)
				.password(password)
				.email(email)
				.role("USER")
				.build();
	}
}
