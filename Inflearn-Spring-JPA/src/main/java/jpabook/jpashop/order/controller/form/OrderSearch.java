package jpabook.jpashop.order.controller.form;

import jpabook.jpashop.order.domain.status.OrderStatus;
import lombok.Getter;

/**
 * @author lob
 * @description order search
 * @since 2021.06.08
 **********************************************************************************************************************/
@Getter
public class OrderSearch {

	private String memberName;
	private OrderStatus orderStatus;

}
