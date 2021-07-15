package com.inflearn.springbootwithjpa.repository;

import com.inflearn.springbootwithjpa.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // 동적 쿼리는 추후 설명 예정정
//   public List<Order> findAll(OrderSearch orderSearch) {
//
//    }
}
