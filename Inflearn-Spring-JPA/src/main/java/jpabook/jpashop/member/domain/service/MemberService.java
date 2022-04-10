package jpabook.jpashop.member.domain.service;

import jpabook.jpashop.member.domain.exception.UserDuplicateException;
import jpabook.jpashop.member.domain.persistence.Member;
import jpabook.jpashop.member.domain.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static jpabook.jpashop.grobal.constant.Constant.Integer.ZERO;

/**
 * @author lob
 * @description member service
 * @since 2021.06.07
 **********************************************************************************************************************/
@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;


	public Long join(Member member) {
		validateDuplicateMember(member);
		return memberRepository.save(member).getId();
	}

	private void validateDuplicateMember(Member member) {
		List<Member> duplicateMembers = memberRepository.findByName(member.getName());
		if (duplicateMembers.size() > ZERO) {
			throw new UserDuplicateException("이미 존재하는 회원입니다.");
		}
	}

	@Transactional(readOnly = true)
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Member findOne(Long memberId) {
		return memberRepository.findById(memberId)
				.orElseThrow(RuntimeException::new);
	}

}
