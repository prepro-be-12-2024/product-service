package vn.test.hub.product.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.test.hub.product.domain.OrderItem;

import java.util.List;

@Getter
@Setter
@Builder
public class CreateOrderRequest {
    private List<OrderItem> orderItems;
    private String amPhone;
}
