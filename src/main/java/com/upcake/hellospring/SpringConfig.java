package com.upcake.hellospring;

import com.upcake.hellospring.aop.TimeTraceAop;
import com.upcake.hellospring.repository.*;
import com.upcake.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration //설정 파일
public class SpringConfig {
//    private DataSource dataSource;  //Jdbc용
//    private EntityManager em;       //JPA용
//
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean //스프링 빈을 내가 등록할 거야
    public MemberService memberService() {
//        return new MemberService(memberRepository());

        //인터페이스를 넣으면 자동으로 스프링이 구현체를 만들어서 Bean으로 등록한다.
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
        //interface는 new가 안되므로 구현체를 return 해준다.
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }

//    이런 식으로 설정에 등록해서 사용해도 된다.
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
}
