package vn.test.hub.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.test.hub.core.searching.FilterParam;
import vn.test.hub.product.domain.Attribute;

import java.util.List;

public interface IAttributeRepository {
    Page<Attribute> findAll(List<FilterParam> filterParams, Pageable pageable);
    Attribute findById(String id);
    Attribute save(Attribute attribute);
}
