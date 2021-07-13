package com.inflearn.springbootwithjpa.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class Book extends Item{ // 계층을 표현하기 위해 extends 상속 사용

    private String author;
    private String isbn;
}
