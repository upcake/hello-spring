package com.upcake.hellospring;

import com.upcake.hellospring.repository.MemberRepository;
import com.upcake.hellospring.repository.MemoryMemberRepository;
import com.upcake.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //설정 파일
public class SpringConfig {

    @Bean //스프링 빈을 내가 등록할 거야
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //interface는 new가 안되므로 구현체를 return 해준다.
        return new MemoryMemberRepository();
    }
}
