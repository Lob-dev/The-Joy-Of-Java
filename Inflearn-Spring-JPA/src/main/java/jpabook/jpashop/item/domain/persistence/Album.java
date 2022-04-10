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
@DiscriminatorValue("A")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Album extends Item {

	@Column(name = "artist")
	private String artist;

	@Column(name = "etc")
	private String etc;


	@Builder
	public Album(Long id, String name, int price, int stockQuantity, List<Category> categories, String artist, String etc) {
		super(id, name, price, stockQuantity, categories);
		this.artist = artist;
		this.etc = etc;
	}

}
