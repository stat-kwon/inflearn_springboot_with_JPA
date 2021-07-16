package com.inflearn.springbootwithjpa.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    private String username; 으로해버리면 API설계도에서 틀어지게 된다. 이렇게 개발을하면 안된다!!! ResponseDto 필요
    private String name;

    @Embedded // 내장 타입으로 만든 Address를 매핑 시켜줌
    private Address address; //Embedable, Embedded 중 하나만 써도 동작하지만 두개다 써준다.

    @JsonIgnore // 순수한 회원 정보만 가지고 오고 싶을 때, 연관관계인 orders를 제외하고 Jason을 뿌려주고 싶을 때 사용한다.
    @OneToMany(mappedBy="member")
   private List<Order> orders = new ArrayList<>();
}
