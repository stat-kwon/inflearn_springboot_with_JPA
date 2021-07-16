package com.inflearn.springbootwithjpa.repository;

import com.inflearn.springbootwithjpa.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

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

   public List<Order> findAll(OrderSearch orderSearch) {
       return em.createQuery("select o from Order o join  o.member m" +
               " where o.status= :status and m.name like :name", Order.class) // 아래부분이 이것이 동적으로 돌아가야 함
               .setParameter("status", orderSearch.getOrderStatus())
               .setParameter("name", orderSearch.getMemberName())
               .setMaxResults(1000) // 페이징 때 쓸 수 있음 최대 1000건
               .getResultList();
   }
}
