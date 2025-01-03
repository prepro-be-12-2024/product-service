package vn.test.hub.product.datasource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.test.hub.common.entity.BaseEntity;

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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
