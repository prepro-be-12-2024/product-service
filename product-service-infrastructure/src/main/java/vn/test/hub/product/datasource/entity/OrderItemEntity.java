package vn.test.hub.product.datasource.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.test.hub.common.entity.BaseEntity;

import java.util.List;

@Entity
@Table(name = "order_items")
@Getter
@Setter
public class OrderItemEntity extends BaseEntity {
    private int quantity;
    private float price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
