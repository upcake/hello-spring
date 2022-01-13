package com.upcake.hellospring.repository;

import com.upcake.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    //JPA를 사용하려면 EntityManager가 있어야 한다.
    //application.properties에 설정한 내용이랑 잘 조합해서 스프링부트가 알아서 EntityManager를 받아온다
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //persist() 영구 저장한다는 메소드
        //JPA가 알아서 insert 쿼리 만들어서 id까지 set해준다
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //find(조회할 타입, 식별자값)
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //select 하는데 entity Member를 select 함
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
