package com.inflearn.springbootwithjpa.service;

import com.inflearn.springbootwithjpa.domain.Address;
import com.inflearn.springbootwithjpa.domain.Member;
import com.inflearn.springbootwithjpa.domain.Order;
import com.inflearn.springbootwithjpa.domain.OrderStatus;
import com.inflearn.springbootwithjpa.domain.item.Book;
import com.inflearn.springbootwithjpa.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();

        Book book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;
        //when
        Long order = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(order);

        Assertions.assertEquals(getOrder.getStatus(), OrderStatus.ORDER,"상품 주문시 상태는 ORDER");
        Assertions.assertEquals(1,getOrder.getOrderItems().size(),"주문한 상품 종류 수가 정확해야 한다.");
        Assertions.assertEquals(10000*orderCount, getOrder.getTotalPrice(),"주문 가격은 가격 * 수량 이다.");
        Assertions.assertEquals(8, book.getStockQuantity(),"주문 이후 수량이 재고량과 일치해야 한다.");
    }

//    @Test
//    public void 상품주문_재고수량초과() throws Exception {
//        //given
//        Member member = createMember();
//        Item item = createBook("시골 JPA", 10000, 10);
//
//        int orderCount = 11;
//
//
//        //when
//        orderService.order(member.getId(), item.getId(), orderCount);
//
//        //then
//        // 잘모르겠음...
//    }

    @Transactional
    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Book item = createBook("시골 JPA", 10000, 22);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assertions.assertEquals(OrderStatus.CANCEL,getOrder.getStatus(),"주문 취소시 상태는 CANCEL이다.");
        Assertions.assertEquals(10,item.getStockQuantity(),"주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

}