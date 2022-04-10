package jpabook.jpashop.member.domain.service.mapper;

import jpabook.jpashop.grobal.embedded.Address;
import jpabook.jpashop.member.controller.form.MemberForm;
import jpabook.jpashop.member.domain.persistence.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {

	MemberMapper mapper = Mappers.getMapper(MemberMapper.class);

	Member toMember(MemberForm form, Address address);

}
