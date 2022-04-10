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
@DiscriminatorValue("M")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie extends Item {

	@Column(name = "director")
	private String director;

	@Column(name = "actor")
	private String actor;


	@Builder
	public Movie(Long id, String name, int price, int stockQuantity, List<Category> categories, String director, String actor) {
		super(id, name, price, stockQuantity, categories);
		this.director = director;
		this.actor = actor;
	}

}
