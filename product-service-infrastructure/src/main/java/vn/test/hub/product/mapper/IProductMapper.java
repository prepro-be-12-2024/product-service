package vn.test.hub.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.test.hub.product.datasource.entity.CategoryEntity;
import vn.test.hub.product.datasource.entity.ProductEntity;
import vn.test.hub.product.domain.Category;
import vn.test.hub.product.domain.Product;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    @Mapping(source = "categoryID", target = "category.id")
    ProductEntity toEntity(Product product);
    @Mapping(source = "category.id", target = "categoryID")
    Product toDTO(ProductEntity productEntity);
}
