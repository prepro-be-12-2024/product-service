package vn.test.hub.product.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OrderItem {
    private String id;
    private String productID;
    private int quantity;
    private float price;
    private String amPhone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private Byte deleted;
    private String orderID;
}
