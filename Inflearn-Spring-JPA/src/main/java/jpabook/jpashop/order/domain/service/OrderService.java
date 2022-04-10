package jpabook.jpashop.order.domain.service;

import jpabook.jpashop.delivery.domain.persistence.Delivery;
import jpabook.jpashop.item.domain.persistence.Item;
import jpabook.jpashop.item.domain.persistence.ItemRepository;
import jpabook.jpashop.item.exception.ItemNotFoundException;
import jpabook.jpashop.member.domain.persistence.Member;
import jpabook.jpashop.member.domain.persistence.MemberRepository;
import jpabook.jpashop.order.controller.form.OrderSearch;
import jpabook.jpashop.order.domain.persistence.Order;
import jpabook.jpashop.order.domain.persistence.OrderItem;
import jpabook.jpashop.order.domain.persistence.OrderCustomRepository;
import jpabook.jpashop.order.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderCustomRepository orderCustomRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;


	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		Member member = memberRepository.findById(memberId)
				.orElseThrow(OrderNotFoundException::new);

		Item item = itemRepository.findById(itemId)
				.orElseThrow(ItemNotFoundException::new);

		Delivery delivery = Delivery.builder()
				.address(member.getAddress())
				.build();

		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		Order order = Order.createOrder(member, delivery, orderItem);
		orderCustomRepository.save(order);
		return order.getId();
	}

	@Transactional
	public void cancelOrder(Long orderId) {
		Order order = orderCustomRepository.findOne(orderId);
		if (order == null) {
			throw new OrderNotFoundException("주문 정보가 존재하지 않습니다.");
		}
		order.cancel();
	}

	public List<Order> findOrders(OrderSearch orderSearch) {
		return orderCustomRepository.findAll(orderSearch);
	}

}
