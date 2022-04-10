package jpabook.jpashop.member.controller;

import jpabook.jpashop.grobal.embedded.Address;
import jpabook.jpashop.member.controller.form.MemberForm;
import jpabook.jpashop.member.domain.persistence.Member;
import jpabook.jpashop.member.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import java.util.List;

import static jpabook.jpashop.member.domain.service.mapper.MemberMapper.mapper;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;


	@GetMapping(value = "/members/new")
	public String createForm(Model model) {
		model.addAttribute("memberForm", new MemberForm());
		return "members/createMemberForm";
	}

	@PostMapping(value = "/members/new")
	public String create(@Valid MemberForm form, BindingResult result) {
		if (result.hasErrors()) { return "members/createMemberForm"; }

		Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
		memberService.join(mapper.toMember(form, address));
		return "redirect:/";
	}

	@GetMapping(value = "/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}

}