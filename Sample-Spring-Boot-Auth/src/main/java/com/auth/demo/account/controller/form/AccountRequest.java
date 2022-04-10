package com.auth.demo.account.controller.form;

import com.auth.demo.account.domain.model.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountRequest {

	@Getter
	@NoArgsConstructor
	public static class SignUp {

		@NotBlank
		private String name;

		@NotBlank
		private String password;

		@Email
		@NotBlank
		private String email;

		public Account toModel() {
			return Account.builder()
					.username(name)
					.password(password)
					.email(email)
					.build();
		}
	}
}