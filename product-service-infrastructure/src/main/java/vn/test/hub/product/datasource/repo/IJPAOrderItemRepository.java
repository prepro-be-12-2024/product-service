package vn.test.hub.product.datasource.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.test.hub.product.datasource.entity.OrderItemEntity;

import java.util.List;

@Repository
public interface IJPAOrderItemRepository extends JpaRepository<OrderItemEntity, String> {

    List<OrderItemEntity> findByOrder_Id(String orderId, Pageable pageable);

}
