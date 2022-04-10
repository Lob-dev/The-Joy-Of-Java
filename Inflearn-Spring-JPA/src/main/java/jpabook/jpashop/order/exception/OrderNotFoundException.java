package jpabook.jpashop.order.exception;

import java.util.NoSuchElementException;

public class OrderNotFoundException extends NoSuchElementException {

	public OrderNotFoundException() {
	}

	public OrderNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
