package com.inflearn.springbootwithjpa.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable  // 내장타입이기 때문에 Embeddable 주게 됨
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
