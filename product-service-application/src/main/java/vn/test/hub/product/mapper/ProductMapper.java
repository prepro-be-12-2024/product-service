package vn.test.hub.product.mapper;

import org.springframework.stereotype.Component;
import vn.test.hub.product.domain.Product;
import vn.test.hub.product.dto.request.product.ProductCreationRequest;
import vn.test.hub.product.dto.request.product.ProductRequest;
import vn.test.hub.product.dto.request.product.ProductUpdateRequest;

@Component
public class ProductMapper {
    public Product toDTO(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .createdBy(request.getCreatedBy())
                .minPrice(request.getMin_price())
                .maxPrice(request.getMax_price())
                .deleted(request.getDeleted())
                .build();
    }

    public Product toDTO(ProductCreationRequest request) {
        return Product.builder()
                .feature(request.getFeature())
                .overview(request.getOverview())
                .name(request.getName())
                .code(request.getCode())
                .categoryId(request.getCategoryId())
                .thumbnail(request.getThumbnail())
                .categoryId(request.getCategoryId())
                .guideId(request.getGuideId())
                .imageId(request.getImageId())
                .attributeValueIds(request.getAttributeValueId())
                .build();
    }

    public Product toDTO(ProductUpdateRequest request) {
        return Product.builder()
                .id(request.getId())
                .feature(request.getFeature())
                .overview(request.getOverview())
                .name(request.getName())
                .code(request.getCode())
                .categoryId(request.getCategoryId())
                .thumbnail(request.getThumbnail())
                .categoryId(request.getCategoryId())
                .guideId(request.getGuideId())
                .imageId(request.getImageId())
                .attributeValueIds(request.getAttributeValueId())
                .build();
    }
}
