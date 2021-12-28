package com.upcake.hellospring.repository;

import com.upcake.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

//굳이 public을 안붙여도 된다. 다른 곳에서 갖다 쓸게 아니기 때문
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //각 테스트 동작이 끝나고 실행되게 하는 어노테이션
    //store를 비우지 않으면 에러가 뜬다
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    //@Test 어노테이션을 붙이면 해당 메소드를 실행할 수 있다.
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //Optional에서 값을 꺼낼때는 get()을 사용
        //System.out.println("result = " + (result == member)); // 이렇게 해도 좋으나 계속 글자로 볼 수는 없다.

        //Assertions.assertEquals(member, result);  //같다면 초록불
        //Assertions.assertEquals(member, null);    //다르면 빨간불

        //assertj의 Assertions
        //요즘에는 이거를 더 많이 사용한다.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //초록불
        Member result = repository.findByName("spring1").get();
        //빨간불
        //Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
