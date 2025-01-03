package vn.test.hub.product.repository;

import org.springframework.data.domain.Pageable;
import vn.test.hub.product.domain.OrderItem;

import java.util.List;

public interface IOrderItemRepository {
    OrderItem create(OrderItem item);
    List<OrderItem> findAllByOrderID(String orderID, Pageable pageable);
}
