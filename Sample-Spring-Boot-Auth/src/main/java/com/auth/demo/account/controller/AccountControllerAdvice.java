package com.auth.demo.account.controller;

import com.auth.demo.account.domain.exception.AccountInfoDuplicateException;
import com.auth.demo.account.domain.exception.AccountNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(0)
@ControllerAdvice(basePackages = "com.auth.demo.account")
public class AccountControllerAdvice {

	@ExceptionHandler(AccountInfoDuplicateException.class)
	protected ResponseEntity<String> handleAccountInfoDuplicateException(AccountInfoDuplicateException exception) {
		return ResponseEntity.status(BAD_REQUEST).body(exception.getLocalizedMessage());
	}

	@ExceptionHandler(AccountNotFoundException.class)
	protected ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException exception) {
		return ResponseEntity.status(NOT_FOUND).body(exception.getLocalizedMessage());
	}
}
