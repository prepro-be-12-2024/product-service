package vn.test.hub.product.service;

import org.springframework.data.domain.Page;
import vn.test.hub.core.pagination.PaginationRequest;
import vn.test.hub.product.domain.Attribute;

public interface IAttributeService {
    Page<Attribute> findAll(Attribute attribute, PaginationRequest pageRequest);
    Attribute findById(String id);
    Attribute save(Attribute attribute);
    Attribute update(Attribute attribute);
    Attribute delete(String id);
}
