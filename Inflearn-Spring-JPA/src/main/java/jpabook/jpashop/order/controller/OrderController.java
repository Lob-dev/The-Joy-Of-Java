package jpabook.jpashop.order.controller;


import jpabook.jpashop.item.domain.persistence.Item;
import jpabook.jpashop.item.domain.service.ItemService;
import jpabook.jpashop.member.domain.persistence.Member;
import jpabook.jpashop.member.domain.service.MemberService;
import jpabook.jpashop.order.controller.form.OrderSearch;
import jpabook.jpashop.order.domain.persistence.Order;
import jpabook.jpashop.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	private final MemberService memberService;
	private final ItemService itemService;


	@GetMapping(value = "/order")
	public String createForm(Model model) {
		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();
		model.addAttribute("members", members);
		model.addAttribute("items", items);
		return "order/orderForm";
	}

	@PostMapping(value = "/order")
	public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId,
						@RequestParam("count") int count) {
		orderService.order(memberId, itemId, count);
		return "redirect:/orders";
	}

	@GetMapping(value = "/orders")
	public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
		List<Order> orders = orderService.findOrders(orderSearch);
		model.addAttribute("orders", orders);
		return "order/orderList";
	}

	@PostMapping(value = "/orders/{orderId}/cancel")
	public String cancelOrder(@PathVariable("orderId") Long orderId) {
		orderService.cancelOrder(orderId);
		return "redirect:/orders";
	}

}
