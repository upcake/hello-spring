package com.upcake.hellospring.repository;

import com.upcake.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //Java 8에 추가된 null을 다루기 위한 기능, id가 null이면 null을 반환한다
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
