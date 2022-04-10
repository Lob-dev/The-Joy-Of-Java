package jpabook.jpashop.item.domain.persistence;

import jpabook.jpashop.item.exception.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static jpabook.jpashop.grobal.constant.Constant.Integer.ZERO;

@Entity
@Getter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Item {

	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "price")
	private int price;

	@Column(name = "stock_quantity")
	private int stockQuantity;

	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();


	public Item(Long id, String name, int price, int stockQuantity, List<Category> categories) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.categories = categories;
	}

	// 비즈니스 로직
	public void addStockQuantity(int stockQuantity) {
		this.stockQuantity += stockQuantity;
	}

	public void removeStockQuantity(int stockQuantity) {
		int restStock = this.stockQuantity - stockQuantity;
		if (restStock < ZERO) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = restStock;
	}

	public void updateNameAndPrice(String name, int price) {
		this.name = name;
		this.price = price;
	}

}