package com.inflearn.springbootwithjpa.service;

import com.inflearn.springbootwithjpa.domain.Member;
import com.inflearn.springbootwithjpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */

    //회원 가입
    @Transactional(readOnly = false)
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 단건 조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
        // 스토리 알아두기
        //얘가 끝나면서 스프링 AOP가 동작하면서 트랜잭셔널 어노테이션에 의해 트랜잭션이 끝나는 시점에서 트랜잭션 커밋이 됨
        //그때 JPA가 flush되고 영속성 커밋도 다해버리는 거죠
    }

//    @Transactional                                              //void나 id를 반환하는 이유는 다음과 같음
//    public Memeber update(Long id, String name) {               // 김영한님의 개발스타일은 커맨드와 쿼리를 철저하게 분리하는 것임
//        Member member = memberRepository.findOne(id);           // 하지만 지금 주석예제는 update하면서 결국 member를 쿼리해버리는 꼴이 되버림
//        member.setName(name);                                   // 다시말하면 받은 파라미터 id를 가지고 쿼리를 조회하는 것이 같이 수행 됨
//        return member;                                          // 따라서 update같은 것들은 id를 반환하거나 void를 쓰는 경향이 있음
//    }
}
