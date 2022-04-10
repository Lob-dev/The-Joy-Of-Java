package com.auth.demo.global.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
		return ResponseEntity.status(BAD_REQUEST).body(exception.getLocalizedMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		return ResponseEntity.status(BAD_REQUEST).body(exception.getLocalizedMessage());
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
		return ResponseEntity.status(BAD_REQUEST).body(exception.getLocalizedMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
		log.info("throw exception = {} || exception message = {} || dateTime = {}", exception.getClass().getSimpleName(), exception.getLocalizedMessage(), LocalDateTime.now());
		return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(exception.getLocalizedMessage());
	}
}
