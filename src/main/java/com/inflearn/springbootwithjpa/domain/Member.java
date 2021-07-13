package com.inflearn.springbootwithjpa.domain;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")  // table보고 name 확인해서 넣을 것
    private Long id;

    private String name;

    @Embedded // 내장 타입으로 만든 Address를 매핑 시켜줌
    private Address address; //Embedable, Embedded 중 하나만 써도 동작하지만 두개다 써준다.

    // member와 order은 1:M 관계
    // mappedBy를 통해서 주종관계를 맺음 종에 해당함 (FK있는 곳을 보통 PK로 함)
    @OneToMany(mappedBy="member") // member에 ctrl+B를 통해 어디서 왔는 지 확인할 것
   private List<Order> orders = new ArrayList<>();
}
