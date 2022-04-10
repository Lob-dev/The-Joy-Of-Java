package jpabook.jpashop.order.domain.persistence;

import jpabook.jpashop.order.controller.form.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderCustomRepository {

	private final EntityManager entityManager;


	public void save(Order order) {
		entityManager.persist(order);
	}

	public Order findOne(Long id) {
		return entityManager.find(Order.class, id);
	}

	public List<Order> findAll(OrderSearch orderSearch) {

		String jpql = "select o From Order o join o.member m";
		boolean isFirstCondition = true;

		//주문 상태 검색
		if (orderSearch.getOrderStatus() != null) {
			if (isFirstCondition) {
				jpql += " where";
				isFirstCondition = false;
			} else {
				jpql += " and";
			}
			jpql += " o.status = :status";
		}

		//회원 이름 검색
		if (StringUtils.hasText(orderSearch.getMemberName())) {
			if (isFirstCondition) {
				jpql += " where";
				isFirstCondition = false;
			} else {
				jpql += " and";
			}
			jpql += " m.name like :name";
		}

		TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class)
				.setMaxResults(1000);
		if (orderSearch.getOrderStatus() != null) {
			query = query.setParameter("status", orderSearch.getOrderStatus());
		}
		if (StringUtils.hasText(orderSearch.getMemberName())) {
			query = query.setParameter("name", orderSearch.getMemberName());
		}
		return query.getResultList();
	}

}
