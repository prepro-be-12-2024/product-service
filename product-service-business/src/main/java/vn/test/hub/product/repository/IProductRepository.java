package vn.test.hub.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.test.hub.core.searching.FilterParam;
import vn.test.hub.product.domain.Product;

import java.util.List;

public interface IProductRepository {
    Page<Product> findAll(List<FilterParam> filterParams, Pageable pageable);
    List<Product> findByIds(List<String> ids);
    Product findById(String id);
    Product save(Product product);
    Product update(Product product);
    Product delete(String id);
}
