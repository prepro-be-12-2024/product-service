package vn.test.hub.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.test.hub.product.datasource.entity.ProductEntity;
import vn.test.hub.product.domain.Product;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    ProductEntity toEntity(Product product);
    Product toDTO(ProductEntity productEntity);
}
