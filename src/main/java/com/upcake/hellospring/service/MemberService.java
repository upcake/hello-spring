package com.upcake.hellospring.service;

import com.upcake.hellospring.domain.Member;
import com.upcake.hellospring.repository.MemberRepository;
import com.upcake.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 중복 회원 X
        /*
        이렇게 작성해도 되지만 안이쁘다
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }); //ifPresent : 값이 있으면
         */

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    //서비스 로직의 네이밍은 비즈니스에 맞게 잡는다. (다른 팀도 알아볼 수 있게)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findone(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
