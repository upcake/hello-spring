package com.upcake.hellospring.service;

import com.upcake.hellospring.domain.Member;
import com.upcake.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {


/*
    //new로 새 객체를 만들면 새 인스턴스기 때문에 뭔가 문제가 있을수도 있다.
    //같은 repository로 테스트를 하는게 맞음
    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
*/

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() { //테스트 메소드는 한글로 만들어도 된다, 빌드 시 테스트 코드는 포함되지 않음
        //given : 무언가가 주어졌는데,
        Member member = new Member();
        member.setName("hello");

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

        //try - catch로도 할 수 있지만 권장되지는 않는다.
/*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다. 123123"); //예외가 터지면 성공한 것
        }
*/


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findone() {
    }
}