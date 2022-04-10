package com.auth.demo.account.domain.exception;

public class AccountIsNotOwnerException extends IllegalStateException {
	public AccountIsNotOwnerException(String errorMessage) {
		super(errorMessage);
	}
}
