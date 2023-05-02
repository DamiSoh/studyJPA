package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class OrderItem {
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    // ++ 생성 메서드 ++ //
    // 생성자 만들어줘서, new OrderItem()으로 생성하지 않도록 해주어야 한다.
    // @NoArgsConstructor(access = AccessLevel.PROTECTED)가 해준다.
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    // ++ 비즈니스 로직 ++//
    public void cancel(){
//        this.getItem() this 생략임
        getItem().addStock(count);
    }

    // ++ 조회 로직 ++ //
    // 1. 주문 상품 전체 가격 조회
    public int getTotalPrice(){
        return getOrderPrice()*getCount();
    }

}
