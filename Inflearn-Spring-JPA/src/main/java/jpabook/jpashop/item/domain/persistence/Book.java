package jpabook.jpashop.item.domain.persistence;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Getter
@DiscriminatorValue("B")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item {

	@Column(name = "author")
	private String author;

	@Column(name = "isbn")
	private String isbn;


	@Builder
	public Book(Long id, String name, int price, int stockQuantity, List<Category> categories, String author, String isbn) {
		super(id, name, price, stockQuantity, categories);
		this.author = author;
		this.isbn = isbn;
	}

}
