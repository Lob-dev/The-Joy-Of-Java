package jpabook.jpashop.item.domain.persistence;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToMany
	@JoinTable(name = "category_item",
			joinColumns = @JoinColumn(name = "category_id"),
			inverseJoinColumns = @JoinColumn(name = "item_id"))
	private List<Item> items = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private List<Category> childs = new ArrayList<>();


	@Builder
	public Category(Long id, String name, List<Item> items, Category parent, List<Category> childs) {
		this.id = id;
		this.name = name;
		this.items = items;
		this.parent = parent;
		this.childs = childs;
	}

	public void addChildCategory(Category category) {
		this.childs.add(category);
		this.setParentInfo(this);
	}

	public void setParentInfo(Category parent) {
		this.parent = parent;
	}

}