package com.auth.demo.sample.controller;

import com.auth.demo.account.domain.exception.AccountIsNotOwnerException;
import com.auth.demo.sample.domain.exception.SampleNotFoundException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Order(0)
@ControllerAdvice(basePackages = "com.auth.demo.sample")
public class SampleExceptionAdvice {

	@ExceptionHandler(AccountIsNotOwnerException.class)
	protected ResponseEntity<String> handleAccountIsNotOwnerException(AccountIsNotOwnerException exception) {
		return ResponseEntity.status(BAD_REQUEST).body(exception.getLocalizedMessage());
	}

	@ExceptionHandler(SampleNotFoundException.class)
	protected ResponseEntity<String> handleSampleNotFoundException(SampleNotFoundException exception) {
		return ResponseEntity.status(BAD_REQUEST).body(exception.getLocalizedMessage());
	}
}
