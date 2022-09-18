package jpabook.jpashop.grobal.exception;

import java.util.NoSuchElementException;

public class NotFoundException extends NoSuchElementException {

	public NotFoundException() {
	}

	public NotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
