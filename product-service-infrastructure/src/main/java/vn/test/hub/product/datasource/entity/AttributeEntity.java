package vn.test.hub.product.datasource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.test.hub.core.entity.BaseEntity;

@Entity
@Table(name = "product_attribute")
@Getter
@Setter
public class AttributeEntity extends BaseEntity {
    private String name;
}
