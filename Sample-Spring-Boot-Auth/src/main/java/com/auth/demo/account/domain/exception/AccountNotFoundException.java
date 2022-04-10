package com.auth.demo.account.domain.exception;

public class AccountNotFoundException extends RuntimeException {
	public AccountNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
