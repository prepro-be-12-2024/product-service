package vn.test.hub.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.test.hub.core.dto.BaseDTO;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@SuperBuilder
public class Product extends BaseDTO {
    private String name;
    private String code;
    private String overview;
    private String feature;
    private String thumbnail;
    private List<String> imageId;
    private String guideId;
    private String categoryId;
    @JsonIgnore
    private List<String> attributeValueIds;
    @JsonIgnore
    private Float price;
    @JsonIgnore
    private Float minPrice;
    @JsonIgnore
    private Float maxPrice;
    private Map<String, String> attributes;
}
