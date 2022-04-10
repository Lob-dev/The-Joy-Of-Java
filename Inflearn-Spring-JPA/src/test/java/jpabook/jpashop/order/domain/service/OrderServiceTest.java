package jpabook.jpashop.order.domain.service;

import jpabook.jpashop.grobal.embedded.Address;
import jpabook.jpashop.item.domain.persistence.Book;
import jpabook.jpashop.item.domain.persistence.Item;
import jpabook.jpashop.item.domain.persistence.ItemRepository;
import jpabook.jpashop.item.exception.NotEnoughStockException;
import jpabook.jpashop.member.domain.persistence.Member;
import jpabook.jpashop.member.domain.service.MemberService;
import jpabook.jpashop.order.domain.persistence.Order;
import jpabook.jpashop.order.domain.persistence.OrderCustomRepository;
import jpabook.jpashop.order.domain.status.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class OrderServiceIntegrationTest {

	@Autowired
	MemberService memberService;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	OrderCustomRepository orderCustomRepository;

	@Autowired
	OrderService orderService;


	@Test
	void 상품주문() {
		// given
		Member member = Member.builder()
				.name("Lob")
				.address(new Address("서울", "강가", "123-123"))
				.build();
		memberService.join(member);

		Item book = Book.builder()
				.name("시골 JPA")
				.price(10000)
				.stockQuantity(10)
				.author("ing")
				.isbn("123554-032134")
				.build();
		itemRepository.save(book);

		// when
		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		// then
		Order order = orderCustomRepository.findOne(orderId);
		assertEquals(OrderStatus.ORDER, order.getStatus(), "상품 주문시 상태는 ORDER");
		assertEquals(1, order.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
		assertEquals(10000 * orderCount, order.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
		assertEquals(8, book.getStockQuantity(), "주문 수량만큼 재고가 줄어야 한다.");
	}

	@Test
	void 상품주문_재고수량초과() {
		// given
		Member member = Member.builder()
				.name("Lob")
				.address(new Address("서울", "강가", "123-123"))
				.build();
		memberService.join(member);

		Item book = Book.builder()
				.name("시골 JPA")
				.price(10000)
				.stockQuantity(10)
				.author("ing")
				.isbn("123554-032134")
				.build();
		itemRepository.save(book);

		int orderCount = 11;
		// when - then
		assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), book.getId(), orderCount));
	}

	@Test
	void 주문취소() {
		// given
		Member member = Member.builder()
				.name("Lob")
				.address(new Address("서울", "강가", "123-123"))
				.build();
		memberService.join(member);

		Item book = Book.builder()
				.name("시골 JPA")
				.price(10000)
				.stockQuantity(10)
				.author("ing")
				.isbn("123554-032134")
				.build();
		itemRepository.save(book);

		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		// when
		orderService.cancelOrder(orderId);

		// then
		Order order = orderCustomRepository.findOne(orderId);

		assertEquals(OrderStatus.CANCEL, order.getStatus(), "주문 취소시 상태는 CANCEL 이다.");
		assertEquals(10, book.getStockQuantity(), "주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");
	}

}