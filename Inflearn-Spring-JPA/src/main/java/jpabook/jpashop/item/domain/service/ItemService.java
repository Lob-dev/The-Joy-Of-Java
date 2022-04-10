package jpabook.jpashop.item.domain.service;

import jpabook.jpashop.item.domain.persistence.Item;
import jpabook.jpashop.item.domain.persistence.ItemRepository;
import jpabook.jpashop.item.exception.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @since       2021.06.07
 * @author      lob
 * @description item service
 **********************************************************************************************************************/
@Service
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository itemRepository;


	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	@Transactional(readOnly = true)
	public List<Item> findItems() {
		return itemRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Item findOne(Long itemId) {
		return itemRepository.findById(itemId)
				.orElseThrow(ItemNotFoundException::new);
	}

	@Transactional
	public void updateItem(Long id, String name, int price) {
		Item item = itemRepository.findById(id)
				.orElseThrow(ItemNotFoundException::new);
		item.updateNameAndPrice(name, price);
	}

}
