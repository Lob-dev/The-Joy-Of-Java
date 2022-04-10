package jpabook.jpashop.order.domain.persistence;

import jpabook.jpashop.item.domain.persistence.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

	@Id
	@GeneratedValue
	@Column(name = "order_item_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item; //주문 상품

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order; //주문

	@Column(name = "order_price")
	private int orderPrice; //주문 가격

	@Column(name = "order_count")
	private int count; //주문 수량


	@Builder
	public OrderItem(Item item, Order order, int orderPrice, int count) {
		this.item = item;
		this.order = order;
		this.orderPrice = orderPrice;
		this.count = count;
	}

	public void setOrderInfo(Order order) {
		this.order = order;
	}

	// 생성 메서드
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
		OrderItem orderItem = OrderItem.builder()
				.item(item)
				.orderPrice(orderPrice)
				.count(count)
				.build();

		item.removeStockQuantity(count);
		return orderItem;
	}

	// 비즈니스 메서드
	public void cancel() {
		getItem().addStockQuantity(count);
	}

	public int getTotalPrice() {
		return (this.orderPrice * this.count);
	}
}
