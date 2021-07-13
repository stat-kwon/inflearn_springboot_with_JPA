package com.inflearn.springbootwithjpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}
