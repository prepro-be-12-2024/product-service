package vn.test.hub.product.mapper;

import org.mapstruct.Mapper;
import vn.test.hub.product.datasource.entity.CategoryEntity;
import vn.test.hub.product.domain.Category;

@Mapper(componentModel = "spring")
public interface ICategoryMapper {
    CategoryEntity toEntity(Category category);
    Category toDTO(CategoryEntity categoryEntity);
}
