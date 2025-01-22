package vn.test.hub.product.dto.request.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.test.hub.core.pagination.PaginationRequest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest extends PaginationRequest {
    @JsonProperty("created_by")
    private String createdBy;
    private String name;
    @PositiveOrZero(message = "Min price must be greater than or equal to 0")
    private Float min_price;
    @PositiveOrZero(message = "Max price must be greater than or equal to 0")
    private Float max_price;
    private Boolean deleted = false;
}

