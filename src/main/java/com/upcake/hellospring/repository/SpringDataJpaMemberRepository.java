package com.upcake.hellospring.repository;

import com.upcake.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//인터페이스 - 인터페이스의 경우에는 extend 사용하여 상속
//인터페이스는 다중 상속이 된다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //findBy~~~ ~~~에 따라서 쿼리를 짜준다
    //select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);

    //외에도 findByNameAnd~~~
    //인터페이스 이름만으로도 개발이 가능하다
}
