package com.upcake.hellospring.controller;

import com.upcake.hellospring.domain.Member;
import com.upcake.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //스프링이 켜질 때 스프링 컨테이너란 통이 생긴다.
    //컨트롤러 어노테이션이 있다면 스프링 컨테이너에 MemberController 객체를 생성해서 넣어둔다.

    private final MemberService memberService;

    //@Autowired를 붙이면 스프링이 스프링 컨테이너에있는 MemberService를 가져다가 연결시켜준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
