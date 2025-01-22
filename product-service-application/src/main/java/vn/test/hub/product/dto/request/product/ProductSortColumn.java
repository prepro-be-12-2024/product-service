package vn.test.hub.product.dto.request.product;

import java.util.Arrays;
import java.util.List;

public class ProductSortColumn {
    private static final List<String> VALID_SORT_COLUMNS = Arrays.asList("name", "created_at", "price");

    public static boolean isValid(String column) {
        return VALID_SORT_COLUMNS.contains(column);
    }


}
