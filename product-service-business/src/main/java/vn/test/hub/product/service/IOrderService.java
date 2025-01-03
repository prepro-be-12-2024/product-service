package vn.test.hub.product.service;

import org.springframework.data.domain.Pageable;
import vn.test.hub.product.domain.Order;

import java.util.List;

public interface IOrderService {
    List<Order> findAll(Integer pageNo, Integer pageSize, String status, String userID, String sortByCreatedAt, String sortByTotalPrice);
    Order create(Order order);
    List<Order> findAllByUserID(String userID);
    Order completeOrder(String orderID, String userID);
}
