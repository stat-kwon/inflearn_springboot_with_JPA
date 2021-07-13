package com.inflearn.springbootwithjpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // order 내장함수와 겹치기 때문에 관례적으로 orders라고 붙임
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // order와 member는 M:1 관계이다.
    @JoinColumn(name = "member_id") // FK의 table에서 이름이 member_id가 된다.
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    // private Date date; -> Java8이전에 쓰던 방식 지금은 아래와 같이 사용
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Enum 타입으로 주문상태를 나타냄 (ORDER, CANCEL)

    //==연관관계 편의 메서드==//
//    public void setMember(Member member) { //멤버를 세팅할때
//        this.member = member; // 반대로 양방향에 넣어줌 이걸 원래대로하면 아래와 같음
//    }

//    public static void main(String[] args) {
//        Member member = new Member();
//        Order order = new Order();
//        member.getOrders().add(order);   //원래대로라면 비즈니스 로직에서 이런식으로 해야 함
//        order.setMember(member);         // 사람이 깜박할 수 있으므로 방향성 두개를 한번에 고려하자는 것임
//    }

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);    // 이런게 연관관계 편의 메서드 실사용때 유용하기 때문
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }                                    // 편의 메서드 작성 위치는 컨트롤 하는쪽에 들고있는게 좋음

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    } // 양방향일때 쓰면 좋다.

}
