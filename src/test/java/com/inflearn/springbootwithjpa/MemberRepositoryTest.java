package com.inflearn.springbootwithjpa;

import com.inflearn.springbootwithjpa.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    @Transactional // test에 있으면 DB를 롤벡해버린다!! 알아둘 것 ( 반복적인 테스트를 위해 )
    @Rollback(false) // 이렇게 하고 돌리면 Test에서 이상없이 확인 가능 함
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memeberA");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);
        // 같은테이블의 DB이므로 실제 메모리 주소도 같은지 확인해보자
        System.out.println("findMember == member: " + (findMember == member));
    }
}