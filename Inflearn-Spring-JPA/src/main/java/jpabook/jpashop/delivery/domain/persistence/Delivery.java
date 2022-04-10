package jpabook.jpashop.delivery.domain.persistence;

import jpabook.jpashop.delivery.domain.status.DeliveryStatus;
import jpabook.jpashop.grobal.embedded.Address;
import jpabook.jpashop.order.domain.persistence.Order;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

	@Id
	@GeneratedValue
	@Column(name = "delivery_id")
	private Long id;

	@OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
	private Order order;

	@Embedded
	@Column(name = "address")
	private Address address;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private DeliveryStatus status; //ENUM [READY(준비), COMP(배송)]


	@Builder
	public Delivery(Long id, Order order, Address address, DeliveryStatus status) {
		this.id = id;
		this.order = order;
		this.address = address;
		this.status = status;
	}

	public void setOrderInfo(Order order) {
		this.order = order;
	}

}