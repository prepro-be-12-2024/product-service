package vn.test.hub.product.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Product {
    private String id;
    private String name;
    private String code;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
    private Byte deleted;
    private String overview;
    private String feature;
    private String thumbnail;
    private String categoryID;
}
