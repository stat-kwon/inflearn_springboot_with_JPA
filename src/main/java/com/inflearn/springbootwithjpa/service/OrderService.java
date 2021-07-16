package com.inflearn.springbootwithjpa.service;

import com.inflearn.springbootwithjpa.domain.Delivery;
import com.inflearn.springbootwithjpa.domain.Member;
import com.inflearn.springbootwithjpa.domain.Order;
import com.inflearn.springbootwithjpa.domain.OrderItem;
import com.inflearn.springbootwithjpa.domain.item.Item;
import com.inflearn.springbootwithjpa.repository.ItemRepository;
import com.inflearn.springbootwithjpa.repository.MemberRepository;
import com.inflearn.springbootwithjpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order); // Cascade로 인해 하나만 저장해도 알아서 하위 계층이 다 저장된다는 것!
        // 주문 서비스 개발 (5분30초 강의) : Cascade에 대한 고민
        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(long orderId) {
        //엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel(); // JPA를 써야하는 이유가 여기서 나타남
    }

    //검색
//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch);
//    }
}
