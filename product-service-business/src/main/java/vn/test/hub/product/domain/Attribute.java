package vn.test.hub.product.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import vn.test.hub.core.dto.BaseDTO;
@Getter
@Setter
@SuperBuilder
public class Attribute extends BaseDTO {
    private String name;
    private String value;
}
