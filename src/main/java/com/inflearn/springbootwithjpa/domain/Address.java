package com.inflearn.springbootwithjpa.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable  // 내장타입이기 때문에 Embeddable 주게 됨
@Getter // 값 타입은 Setter를 제공안해야 함 Immutable한 성격을 갖기 때문
public class Address {

    private String city;
    private String street;
    private String zipcode;

    // public도 가능하지만 protected를 해준 이유는
    // 이걸보고 유지보수 할때 아 이부분은 건들지 말자! 라는 경각심을 주는 표시라고 생각하면 됨됨
    // new로 생성 못하도록 막는 것
    // JPA가 이런 제약을 두는 이유는 JPA 구현 라이브러리가 객체를 생성할 때 리플랙션 같은 기술을 사용할 수 있도록 지원해야 하기 때문임
   protected Address() {

    }

    public Address(String city, String street, String zipcode) { //지금과 같이 필요한 경우 생성자를 만들어서 사용하는 방법을 이용함
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
