package com.inflearn.springbootwithjpa.domain.item;

import com.inflearn.springbootwithjpa.domain.Category;
import com.inflearn.springbootwithjpa.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속관계 전략을 부모클래스에서 잡아줘야 함
@DiscriminatorColumn(name="dtype")
@Getter @Setter
public abstract class Item { //구현체를 만들기 때문에 추상 클래스로 만들어 줌

    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비지니스 로직==//
    public void addStock(int quantity) {
        this.stockQuantity = quantity;
    }

    public void removeStock(int quantity) {
        int resStock = this.stockQuantity - quantity;
        if (resStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = resStock;
    }
}
