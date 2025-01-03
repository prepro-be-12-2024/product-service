package vn.test.hub.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.test.hub.product.domain.Category;
import vn.test.hub.product.repository.ICategoryRepository;
import vn.test.hub.product.service.ICategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        // validate and logic here.
        return categoryRepository.findAll();
    }
}
