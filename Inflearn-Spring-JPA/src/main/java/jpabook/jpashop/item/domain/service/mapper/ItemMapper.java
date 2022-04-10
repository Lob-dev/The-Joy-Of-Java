package jpabook.jpashop.item.domain.service.mapper;

import jpabook.jpashop.item.controller.form.BookForm;
import jpabook.jpashop.item.domain.persistence.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemMapper {

	ItemMapper mapper = Mappers.getMapper(ItemMapper.class);

	Book toBook(BookForm form);

	BookForm toForm(Book book);
}
