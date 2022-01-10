package com.upcake.hellospring;

import com.upcake.hellospring.repository.JdbcMemberRepository;
import com.upcake.hellospring.repository.JdbcTemplateMemberRepository;
import com.upcake.hellospring.repository.MemberRepository;
import com.upcake.hellospring.repository.MemoryMemberRepository;
import com.upcake.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration //설정 파일
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean //스프링 빈을 내가 등록할 거야
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //interface는 new가 안되므로 구현체를 return 해준다.
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
    }
}
