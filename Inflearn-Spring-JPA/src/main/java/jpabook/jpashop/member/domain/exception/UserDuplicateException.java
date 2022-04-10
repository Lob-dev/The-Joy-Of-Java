package jpabook.jpashop.member.domain.exception;

/**
 * @author lob
 * @description user duplicate exception
 * @since 2021.06.08
 **********************************************************************************************************************/
public class UserDuplicateException extends IllegalStateException{

	public UserDuplicateException() {
	}

	public UserDuplicateException(String errorMessage) {
		super(errorMessage);
	}

}
