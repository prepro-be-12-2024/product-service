package vn.test.hub.product.service;

import org.springframework.data.domain.Page;
import vn.test.hub.core.pagination.PaginationRequest;
import vn.test.hub.product.domain.Product;

import java.util.List;

public interface IProductService {
    Page<Product> findAll(Product product, PaginationRequest paginationRequest) throws IllegalAccessException;
    List<Product> findByIds(List<String> ids);
    Product findById(String id);
    Product save(Product product);
    Product update(Product product);
    Product delete(String id);
}
