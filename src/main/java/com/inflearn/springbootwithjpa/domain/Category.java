package com.inflearn.springbootwithjpa.domain;

import com.inflearn.springbootwithjpa.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    // 더 추가가 불가능하여 확장성 고려해 N:M 모델은 쓰지 않는다. // JPA에서 되긴 된다를 보여줄려고 만들고 있는 것
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent") //다른 엔티티껴서 하는 걸 하나의 테이블에서 다 만들어 주는 것것
    private List<Category> child = new ArrayList<>();
}
