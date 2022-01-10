package com.upcake.hellospring.service;

import com.upcake.hellospring.domain.Member;
import com.upcake.hellospring.repository.MemberRepository;
import com.upcake.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
데이터베이스는 트랜잭션이라는 개념이 있다.
보통 insert를 한 다음에 commit을 해줘야 DB에 반영이된다.
@Transactional을 테스트케이스에 달면 테스트 트랜잭션을 실행하고, 테스트가 끝나면 rollback을 해준다.
==> 테스트 결과가 반영이 안됨!!
 */

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    //테스트 케이스는 그냥 제일 편한 것을 사용하는것이 좋다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() { //테스트 메소드는 한글로 만들어도 된다, 빌드 시 테스트 코드는 포함되지 않음
        //given : 무언가가 주어졌는데,
        Member member = new Member();
        member.setName("spring");

        //when  : 이걸로 실행했을때,
        Long saveId = memberService.join(member);

        //then  : 결과가 이렇게 나와야 한다.
        Member findMember = memberService.findone(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        //뒤에 로직을 실행했을 때, 앞에 예외가 터져야 한다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }
}