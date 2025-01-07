package vn.test.hub.product.mapper;

import org.mapstruct.*;
import vn.test.hub.product.datasource.entity.OrderEntity;
import vn.test.hub.product.datasource.entity.OrderItemEntity;
import vn.test.hub.product.domain.Order;

@Mapper(componentModel = "spring", uses = {IOrderItemMapper.class})
public interface IOrderMapper {

    @Mapping(target = "orderItemEntities", ignore = true)
    OrderEntity toEntity(Order dto);

    Order toDTO(OrderEntity entity);

    @AfterMapping
    default void mapOrderItems(Order dto, @MappingTarget OrderEntity entity) {
        if (dto.getOrderItems() != null) {
            entity.getOrderItemEntities().clear(); // Xóa danh sách cũ để tránh trùng lặp
            dto.getOrderItems().forEach(orderItemDTO -> {
                OrderItemEntity orderItemEntity = new OrderItemEntity();
                orderItemEntity.setQuantity(orderItemDTO.getQuantity());
                orderItemEntity.setPrice(orderItemDTO.getPrice());
                orderItemEntity.setOrder(entity); // Gán order cho từng OrderItemEntity
                entity.getOrderItemEntities().add(orderItemEntity);
            });
        }
    }
}


