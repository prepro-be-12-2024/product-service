package vn.test.hub.product.datasource.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.test.hub.core.entity.BaseEntity;

@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductEntity extends BaseEntity {
    private String overview;
    private String feature;
    private String name;
    private String code;
    private String thumbnail;

    @Column(name = "category_id")
    private String categoryId;
}
