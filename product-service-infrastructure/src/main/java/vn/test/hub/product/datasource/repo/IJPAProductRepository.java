package vn.test.hub.product.datasource.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.test.hub.product.datasource.entity.ProductEntity;

@Repository
public interface IJPAProductRepository extends JpaRepository<ProductEntity, String> {
}
