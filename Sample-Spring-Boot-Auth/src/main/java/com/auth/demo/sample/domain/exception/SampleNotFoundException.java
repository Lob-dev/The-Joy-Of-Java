package com.auth.demo.sample.domain.exception;

public class SampleNotFoundException extends RuntimeException {
	public SampleNotFoundException(String errorMessage) {
		super(errorMessage);
	}
}
