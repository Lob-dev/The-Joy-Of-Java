package jpabook.jpashop.order.domain.persistence;

import jpabook.jpashop.delivery.domain.persistence.Delivery;
import jpabook.jpashop.delivery.domain.status.DeliveryStatus;
import jpabook.jpashop.member.domain.persistence.Member;
import jpabook.jpashop.order.domain.status.OrderStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member; //주문 회원

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_id")
	private Delivery delivery;

	@Column(name = "order_date")
	private LocalDateTime orderDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private OrderStatus status;


	@Builder
	public Order(Long id, Member member, List<OrderItem> orderItems, Delivery delivery, LocalDateTime orderDate, OrderStatus status) {
		this.id = id;
		this.member = member;
		this.orderItems = orderItems;
		this.delivery = delivery;
		this.orderDate = orderDate;
		this.status = status;
	}

	// 양방향 연관관계에서는 이러한 매핑 메서드를 사용해야 함
	public void setMember(Member member) {
		this.member = member;
		this.member.getOrders().add(this);
	}

	public void addOrderItem(OrderItem orderItem) {
		if (this.orderItems == null) {
			this.orderItems = new ArrayList<>();
		}
		this.orderItems.add(orderItem);
		orderItem.setOrderInfo(this);
	}

	public void setDeliveryInfo(Delivery delivery) {
		this.delivery = delivery;
		delivery.setOrderInfo(this);
	}

	// 생성 메서드
	public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
		Order order = Order.builder()
				.member(member)
				.delivery(delivery)
				.status(OrderStatus.ORDER)
				.orderDate(LocalDateTime.now())
				.build();

		for (OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);
		}
		return order;
	}

	// 비즈니스 메서드
	public void cancel() {
		if(delivery.getStatus() == DeliveryStatus.COMP) {
			throw new IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.");
		}

		this.status = OrderStatus.CANCEL;
		for (OrderItem orderItem : orderItems) {
			orderItem.cancel();
		}
	}

	public int getTotalPrice() {
		return orderItems.stream()
				.mapToInt(OrderItem::getTotalPrice)
				.sum();
	}

}
