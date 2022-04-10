package com.auth.demo.account.domain.model;

import com.auth.demo.account.domain.persistence.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;


@Getter
public class Account implements UserDetails {

	private final Long id;
	private String password;
	private final String username;
	private final String email;
	private final String role;
	private final Boolean nonLocked;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(role));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return nonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Builder
	public Account(Long id, String username, String password, String email, String role, Boolean nonLocked) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.nonLocked = nonLocked;
	}

	public String getEmail() {
		return email;
	}

	public void encodedPassword(PasswordEncoder encoder) {
		this.password = encoder.encode(password);
	}

	public static Account from(AccountEntity entity) {
		return new Account(entity.getId(), entity.getUsername(), entity.getPassword(), entity.getEmail(), entity.getRole(), entity.getNonLocked());
	}

	public AccountEntity toEntity() {
		return AccountEntity.builder()
				.id(id)
				.username(username)
				.password(password)
				.email(email)
				.role("ROLE_USER")
				.nonLocked(true)
				.build();
	}

}
