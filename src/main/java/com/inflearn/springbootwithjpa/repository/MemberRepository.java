package com.inflearn.springbootwithjpa.repository;

import com.inflearn.springbootwithjpa.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext // 엔티티 매니저를 주입시켜줌
    private EntityManager em;

    // Member 영속화
    public void save(Member member) {
        em.persist(member);
    }

    // 단건 조회
    public Member fineOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList(); // ( 쿼리, 반환타입 ) // JQuary에서 m이 객체라고 생각하면 됨
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name =:name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}

/*
query.getResultList() : 결과를 예제로 반환한다. 없다면 빈 컬렉션을 반환
query.getSingleResult() : 결과가 정확히 하나 일때 사용, 1개가 아니라면 예외가 발생
 */
