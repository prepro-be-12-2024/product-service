package vn.test.hub.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.test.hub.common.exception.ForbiddenException;
import vn.test.hub.common.exception.NotFoundException;
import vn.test.hub.product.domain.Order;
import vn.test.hub.product.repository.IOrderRepository;
import vn.test.hub.product.service.IOrderService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final IOrderRepository orderRepository;

    @Override
    public List<Order> findAll(Integer pageNo, Integer pageSize, String status, String userID, String sortByCreatedAt, String sortByTotalPrice) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return orderRepository.findAll(pageable, status, userID, sortByCreatedAt, sortByTotalPrice);
    }

    @Override
    @Transactional
    public Order create(Order order) {
        return orderRepository.create(order);
    }

    @Override
    public List<Order> findAllByUserID(String userID) {
        return orderRepository.findAllByUserID(userID);
    }

    @Override
    public Order completeOrder(String orderID, String userID) {
        Optional<Order> orderOptional = orderRepository.findByID(orderID);

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            if (order.getUserID().equals(userID)) {
                return orderRepository.completeOrder(order);
            } else
                throw new ForbiddenException(HttpStatus.FORBIDDEN.getReasonPhrase());
        } else
            throw new NotFoundException(HttpStatus.NOT_FOUND.getReasonPhrase());


    }
}
