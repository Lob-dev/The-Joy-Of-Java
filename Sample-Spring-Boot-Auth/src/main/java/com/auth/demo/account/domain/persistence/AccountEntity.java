package com.auth.demo.account.domain.persistence;

import com.auth.demo.global.domain.persistence.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountEntity extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "roles", nullable = false)
	private String role;

	@Column(name = "non_locked", nullable = false)
	private Boolean nonLocked;

	public void lockMember() {
		this.nonLocked = false;
	}

	@Builder
	public AccountEntity(Long id, String username, String password, String email, String role, Boolean nonLocked) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.nonLocked = nonLocked;
	}
}