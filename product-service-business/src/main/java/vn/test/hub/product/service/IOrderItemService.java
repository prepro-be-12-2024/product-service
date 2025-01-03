package vn.test.hub.product.service;

import vn.test.hub.product.domain.OrderItem;

import java.util.List;

public interface IOrderItemService {
    OrderItem create(OrderItem item);
    List<OrderItem> findAllByOrderID(Integer pageNo, Integer pageSize, String orderID);
}
