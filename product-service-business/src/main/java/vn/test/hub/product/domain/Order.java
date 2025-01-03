package vn.test.hub.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Order {
    private String id;
    private String userID;
    private String status;
    private String amPhone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private Byte deleted;
    private Float totalPrice;
    @JsonIgnore
    private List<OrderItem> orderItems;
}
