package vn.test.hub.product.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import vn.test.hub.core.searching.FilterParam;
import vn.test.hub.product.domain.Attribute;
import vn.test.hub.product.repository.IAttributeRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AttributeRepository implements IAttributeRepository {
    @Override
    public Page<Attribute> findAll(List<FilterParam> filterParams, Pageable pageable) {
        return null;
    }

    @Override
    public Attribute findById(String id) {
        return null;
    }

    @Override
    public Attribute save(Attribute attribute) {
        return null;
    }
}
