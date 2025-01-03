package vn.test.hub.product.mapper;

import org.springframework.stereotype.Component;
import vn.test.hub.product.domain.Order;
import vn.test.hub.product.dto.request.CreateOrderRequest;

@Component
public class OrderMapper {
    public Order toOrder(CreateOrderRequest orderRequest) {
        return Order.builder()
                .amPhone(orderRequest.getAmPhone())
                .orderItems(orderRequest.getOrderItems()).build();
    }
}
