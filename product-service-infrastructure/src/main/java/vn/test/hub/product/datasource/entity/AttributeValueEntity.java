package vn.test.hub.product.datasource.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.test.hub.core.entity.BaseEntity;

@Entity
@Table(name = "product_attribute_value")
@Getter
@Setter
public class AttributeValueEntity extends BaseEntity {
    @Column(name = "product_id")
    private String productId;

    @Column(name = "attribute_id")
    private String attributeId;

    private String value;
}
