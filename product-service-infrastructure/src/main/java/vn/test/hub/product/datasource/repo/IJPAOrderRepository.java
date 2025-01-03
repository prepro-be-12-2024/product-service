package vn.test.hub.product.datasource.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.test.hub.product.datasource.entity.CategoryEntity;
import vn.test.hub.product.datasource.entity.OrderEntity;

import java.util.List;

@Repository
public interface IJPAOrderRepository extends JpaRepository<OrderEntity, String>, JpaSpecificationExecutor<OrderEntity> {
    List<OrderEntity> findAllByUserID(String userID);

    @Modifying
    @Query("update OrderEntity o set o.status = :status, o.updatedAt = current_timestamp where o.id = :id")
    void changeStatus(@Param("status") String status, @Param("id") String id);
}
