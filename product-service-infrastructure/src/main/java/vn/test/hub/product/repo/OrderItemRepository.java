package vn.test.hub.product.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.test.hub.product.datasource.entity.OrderEntity;
import vn.test.hub.product.datasource.entity.OrderItemEntity;
import vn.test.hub.product.datasource.repo.IJPAOrderItemRepository;
import vn.test.hub.product.datasource.repo.IJPAOrderRepository;
import vn.test.hub.product.domain.Order;
import vn.test.hub.product.domain.OrderItem;
import vn.test.hub.product.mapper.IOrderItemMapper;
import vn.test.hub.product.mapper.IOrderMapper;
import vn.test.hub.product.repository.IOrderItemRepository;
import vn.test.hub.product.repository.IOrderRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemRepository implements IOrderItemRepository {

    private final IJPAOrderItemRepository orderItemRepository;
    private final IOrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderItem create(OrderItem item) {
        OrderItemEntity item1 = orderItemMapper.toEntity(item);
        OrderItemEntity entity = orderItemRepository.saveAndFlush(item1);
        return orderItemMapper.toDTO(entity);
    }

    @Override
    public List<OrderItem> findAllByOrderID(String orderID, Pageable pageable) {
        return orderItemRepository.findByOrder_Id(orderID, pageable).stream().map(
                orderItemMapper::toDTO
        ).toList();
    }


}
