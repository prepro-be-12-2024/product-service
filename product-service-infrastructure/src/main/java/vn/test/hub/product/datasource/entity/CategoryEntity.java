package vn.test.hub.product.datasource.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.test.hub.core.entity.BaseEntity;

@Entity
@Table(name = "category")
@Getter
@Setter
public class CategoryEntity extends BaseEntity {
    private String name;
    private String code;

    @Column(name = "parent_id")
    private String parentId;
}
