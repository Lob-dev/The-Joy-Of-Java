package jpabook.jpashop.item.exception;

public class NotEnoughStockException extends IllegalArgumentException {

	public NotEnoughStockException(String exceptionMessage) {
		super(exceptionMessage);
	}

	public NotEnoughStockException(String exceptionMessage, Throwable cause) {
		super(exceptionMessage, cause);
	}

}
