package vn.test.hub.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.test.hub.product.datasource.entity.OrderItemEntity;
import vn.test.hub.product.domain.OrderItem;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IOrderItemMapper {
    @Mapping(source = "productID", target = "product.id")
    @Mapping(source = "orderID", target = "order.id")
    @Mapping(target = "order", ignore = true)
    OrderItemEntity toEntity(OrderItem dto);
    @Mapping(source = "order.id", target = "orderID")
    @Mapping(source = "product.id", target = "productID")
    OrderItem toDTO(OrderItemEntity entity);
}

