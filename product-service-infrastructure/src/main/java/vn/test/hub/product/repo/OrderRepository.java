package vn.test.hub.product.repo;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import vn.test.hub.product.datasource.entity.OrderEntity;
import vn.test.hub.product.datasource.entity.OrderItemEntity;
import vn.test.hub.product.datasource.repo.IJPAOrderRepository;
import vn.test.hub.product.domain.Order;
import vn.test.hub.product.mapper.IOrderMapper;
import vn.test.hub.product.repository.IOrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepository implements IOrderRepository {

    private final IJPAOrderRepository jpaOrderRepository;
    private final IOrderMapper orderMapper;

    private boolean paramIsNotEmptyAndNull(String param) {
        if (param == null)
            return false;
        else return !param.isEmpty();
    }

    private float getTotalPrice(OrderEntity order) {
        float totalPrice = 0;
        List<OrderItemEntity> orderItems = order.getOrderItemEntities();
        for (OrderItemEntity entity : orderItems) {
            totalPrice += entity.getPrice() * entity.getQuantity();
        }
        return totalPrice;
    }

    @Override
    public List<Order> findAll(Pageable pageable, String status, String userID, String sortByCreatedAt, String sortByTotalPrice) {
        Specification<OrderEntity> specification = ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            List<jakarta.persistence.criteria.Order> orders = new ArrayList<>();
            assert query != null;

            predicates.add(criteriaBuilder.equal(root.get("deleted"), (byte) 0));

            if (paramIsNotEmptyAndNull(status))
                predicates.add(criteriaBuilder.like(root.get("status"), "%" + status + "%"));

            if (paramIsNotEmptyAndNull(userID))
                predicates.add(criteriaBuilder.like(root.get("userID"), "%" + userID + "%"));

            if (paramIsNotEmptyAndNull(sortByCreatedAt)) {
                if ("DESC".equalsIgnoreCase(sortByCreatedAt))
                    orders.add(criteriaBuilder.desc(root.get("created_at")));
                else
                    orders.add(criteriaBuilder.asc(root.get("created_at")));
            }

            if (paramIsNotEmptyAndNull(sortByTotalPrice)) {

                Expression<Float> totalValue = criteriaBuilder.sum(
                        criteriaBuilder.prod(
                                root.join("orderItemEntities").get("price"),
                                root.join("orderItemEntities").get("quantity")
                        )
                );

                query.groupBy(root.get("id"));

                if ("DESC".equalsIgnoreCase(sortByTotalPrice))
                    orders.add(criteriaBuilder.desc(totalValue));
                else
                    orders.add(criteriaBuilder.asc(totalValue));
            }

            query.where(predicates.toArray(new Predicate[0]));

            query.orderBy(orders);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        });

        return jpaOrderRepository.findAll(specification, pageable).stream().map(
                entity -> {
                    Order order = orderMapper.toDTO(entity);
                    order.setTotalPrice(getTotalPrice(entity));
                    return order;
                }
        ).toList();
    }

    @Override
    @Transactional
    public Order create(Order order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        orderEntity.setDeleted((byte) 0);
        orderEntity.setStatus("PENDING");

        // Ánh xạ danh sách OrderItems
        orderMapper.mapOrderItems(order, orderEntity);

        OrderEntity savedEntity = jpaOrderRepository.save(orderEntity);
        return orderMapper.toDTO(savedEntity);
    }


    @Override
    public List<Order> findAllByUserID(String userID) {
        return jpaOrderRepository.findAllByUserID(userID).stream().map(orderMapper::toDTO).toList();
    }

    @Override
    @Transactional
    public Order completeOrder(Order order) {
        order.setStatus("COMPLETE");
        jpaOrderRepository.changeStatus("COMPLETE", order.getId());
        return order;
    }

    @Override
    public Optional<Order> findByID(String orderID) {
        return jpaOrderRepository.findById(orderID).map(orderMapper::toDTO);
    }
}
