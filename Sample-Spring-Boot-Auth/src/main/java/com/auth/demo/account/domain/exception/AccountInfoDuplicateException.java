package com.auth.demo.account.domain.exception;

public class AccountInfoDuplicateException extends IllegalArgumentException {
	public AccountInfoDuplicateException(String errorMessage) {
		super(errorMessage);
	}
}
