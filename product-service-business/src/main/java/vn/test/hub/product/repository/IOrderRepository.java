package vn.test.hub.product.repository;

import org.springframework.data.domain.Pageable;
import vn.test.hub.product.domain.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderRepository {
    List<Order> findAll(Pageable pageable, String status, String userID, String sortByCreatedAt, String sortByTotalPrice);
    Order create(Order order);
    List<Order> findAllByUserID(String userID);
    Order completeOrder(Order order);
    Optional<Order> findByID(String orderID);
}
