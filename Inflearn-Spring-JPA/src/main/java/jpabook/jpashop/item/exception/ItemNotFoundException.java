package jpabook.jpashop.item.exception;

import java.util.NoSuchElementException;

public class ItemNotFoundException extends NoSuchElementException {
	public ItemNotFoundException() {
	}

	public ItemNotFoundException(String exceptionMessage) {
		super(exceptionMessage);
	}
}
