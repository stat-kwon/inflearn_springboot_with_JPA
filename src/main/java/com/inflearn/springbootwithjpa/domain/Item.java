package com.inflearn.springbootwithjpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter

public abstract class Item { //구현체를 만들기 때문에 추상 클래스로 만들어 줌

    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;
    //20분 계층형으로 가즈아
}
