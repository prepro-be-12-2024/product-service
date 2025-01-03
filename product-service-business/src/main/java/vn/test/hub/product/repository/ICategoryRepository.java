package vn.test.hub.product.repository;

import vn.test.hub.product.domain.Category;

import java.util.List;

public interface ICategoryRepository {
    List<Category> findAll();
}
