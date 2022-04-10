package jpabook.jpashop.item.controller;

import jpabook.jpashop.item.controller.form.BookForm;
import jpabook.jpashop.item.domain.persistence.Book;
import jpabook.jpashop.item.domain.persistence.Item;
import jpabook.jpashop.item.domain.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static jpabook.jpashop.item.domain.service.mapper.ItemMapper.mapper;

@Controller
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;


	@GetMapping(value = "/items/new")
	public String createForm(Model model) {
		model.addAttribute("form", new BookForm());
		return "items/createItemForm";
	}

	@PostMapping(value = "/items/new")
	public String create(BookForm form) {
		itemService.saveItem(mapper.toBook(form));
		return "redirect:/items";
	}

	@GetMapping(value = "/items")
	public String list(Model model) {
		List<Item> items = itemService.findItems();
		model.addAttribute("items", items);
		return "items/itemList";
	}

	@GetMapping(value = "/items/{itemId}/edit")
	public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
		BookForm form = mapper.toForm((Book) itemService.findOne(itemId));
		model.addAttribute("form", form);
		return "items/updateItemForm";
	}

	@PostMapping(value = "/items/{itemId}/edit")
	public String updateItem(@ModelAttribute("form") BookForm form) {
		itemService.updateItem(form.getId(), form.getName(), form.getPrice());
		return "redirect:/items";
	}

}