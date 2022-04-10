package jpabook.jpashop.member.domain.service;

import jpabook.jpashop.member.domain.persistence.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

	@Autowired
	MemberService memberService;

	@Test
	void 회원가입() {
		// given
		Member member = Member.builder()
				.name("lob")
				.build();

		// when
		Long savedId = memberService.join(member);

		// then
		assertEquals(member, memberService.findOne(savedId));
	}

	@Test
	void 중복_회원이_있다면_예외가_발생해야한다() {

		// given
		Member member1 = Member.builder()
							.name("lob")
							.build();

		Member member2 = Member.builder()
							.name("lob")
							.build();

		// when
		memberService.join(member1);

		// then
		assertThrows(IllegalStateException.class, () -> memberService.join(member2));
	}

}