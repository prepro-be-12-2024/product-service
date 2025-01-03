package vn.test.hub.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.test.hub.product.domain.Order;
import vn.test.hub.product.domain.OrderItem;
import vn.test.hub.product.repository.IOrderItemRepository;
import vn.test.hub.product.repository.IOrderRepository;
import vn.test.hub.product.service.IOrderItemService;
import vn.test.hub.product.service.IOrderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements IOrderItemService {
    private final IOrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public OrderItem create(OrderItem item) {
        return orderItemRepository.create(item);
    }

    @Override
    public List<OrderItem> findAllByOrderID(Integer pageNo, Integer pageSize, String orderID) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return orderItemRepository.findAllByOrderID(orderID, pageable);
    }
}
