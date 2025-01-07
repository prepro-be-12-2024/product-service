package vn.test.hub.product.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import vn.test.hub.product.datasource.repo.IJPACategoryRepository;
import vn.test.hub.product.domain.Category;
import vn.test.hub.product.mapper.ICategoryMapper;
import vn.test.hub.product.repository.ICategoryRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository implements ICategoryRepository {

    private final IJPACategoryRepository jpaCategoryRepository;
    @Qualifier("ICategoryMapperImpl")
    private final ICategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        return jpaCategoryRepository.findAll().stream()
                .map(categoryMapper::toDTO).toList();
    }
}
