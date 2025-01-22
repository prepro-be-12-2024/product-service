package vn.test.hub.product.datasource.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.test.hub.product.datasource.entity.AttributeValueEntity;

import java.util.List;

public interface IJPAAttributeValueRepository extends JpaRepository<AttributeValueEntity, String> {
    List<AttributeValueEntity> findAllByProductIdIn(List<String> productIds);
    List<AttributeValueEntity> findAllByProductId(String productId);
}
