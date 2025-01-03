package vn.test.hub.product.service;

import vn.test.hub.product.domain.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
}
