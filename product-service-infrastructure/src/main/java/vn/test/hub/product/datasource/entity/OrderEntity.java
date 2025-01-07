package vn.test.hub.product.datasource.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.test.hub.common.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity extends BaseEntity {
    private String status;
    private String amPhone;
    @Column(name = "user_id")
    private String userID;
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItemEntities = new ArrayList<>();
}
