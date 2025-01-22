package vn.test.hub.product.dto.request.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.test.hub.core.validator.anotation.IdConstraint;

import java.util.List;

@Getter
@Setter
@Builder
public class ProductCreationRequest {
    @NotBlank(message = "Name can not be blank")
    private String name;
    @NotBlank(message = "Code can not be blank")
    private String code;
    @NotBlank(message = "Overview can not be blank")
    private String overview;
    @NotBlank(message = "Feature can not be blank")
    private String feature;
    @NotBlank(message = "Thumbnail can not be blank")
    private String thumbnail;
    @NotEmpty(message = "Image cant not br empty")
    private List<@IdConstraint String> imageId;
    @NotBlank(message = "Guide can not be blank")
    private String guideId;
    @NotBlank(message = "Category can not be blank")
    private String categoryId;
    @NotEmpty(message = "Attribute value can not be empty")
    private List<String> attributeValueId;
}
