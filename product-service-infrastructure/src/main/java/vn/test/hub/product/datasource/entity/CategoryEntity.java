package vn.test.hub.product.datasource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.test.hub.common.entity.BaseEntity;

import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
public class CategoryEntity extends BaseEntity {
    private String name;
    private String code;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;
}
