package jpabook.jpashop.member.domain.persistence;

import jpabook.jpashop.grobal.embedded.Address;
import jpabook.jpashop.order.domain.persistence.Order;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Embedded
	@Column(name = "address")
	private Address address;

	@OneToMany(mappedBy = "member")
	private List<Order> orders = new ArrayList<>();


	@Builder
	public Member(String name, Address address, List<Order> orders) {
		this.name = name;
		this.address = address;
		this.orders = orders;
	}

}
