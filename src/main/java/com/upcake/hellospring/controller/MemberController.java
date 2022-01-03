package com.upcake.hellospring.controller;

import com.upcake.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
