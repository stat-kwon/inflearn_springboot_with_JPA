package com.inflearn.springbootwithjpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders") // order 내장함수와 겹치기 때문에 관례적으로 orders라고 붙임
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne // order와 member는 M:1 관계이다.
    @JoinColumn(name="member_id") // FK의 table에서 이름이 member_id가 된다.
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    // private Date date; -> Java8이전에 쓰던 방식 지금은 아래와 같이 사용
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Enum 타입으로 주문상태를 나타냄 (ORDER, CANCEL)
}
