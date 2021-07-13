package com.inflearn.springbootwithjpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // ORIDNAL의 경우 index로 들어가기 때문에 추가되면 골치아파져서 쓰지 않음, 문자로 인식하는 String 쓸것
    private DeliveryStatus status; // Enum type : READY, COMP
}
