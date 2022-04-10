package com.auth.demo.account.controller.form;

import com.auth.demo.account.domain.persistence.AccountEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountResponse {

	@Getter
	@NoArgsConstructor
	public static class UserInfo {
		private String name;

		public UserInfo(String name) {
			this.name = name;
		}

		public static UserInfo from(AccountEntity account) {
			return new UserInfo(account.getUsername());
		}
	}
}