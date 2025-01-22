package vn.test.hub.product.datasource.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.test.hub.product.datasource.entity.AttributeEntity;

import java.util.Optional;

public interface IJPAAttributeRepository extends JpaRepository<AttributeEntity, String> {
    AttributeEntity findByName(String name);
    Optional<AttributeEntity> findByIdAndDeleted(String id, boolean deleted);
}
