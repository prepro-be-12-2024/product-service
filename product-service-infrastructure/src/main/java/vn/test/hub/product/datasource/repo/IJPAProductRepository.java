package vn.test.hub.product.datasource.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.test.hub.product.datasource.entity.ProductEntity;

public interface IJPAProductRepository extends JpaRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {
}
