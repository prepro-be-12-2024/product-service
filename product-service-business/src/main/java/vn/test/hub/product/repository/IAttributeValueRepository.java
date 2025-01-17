package vn.test.hub.product.repository;

import vn.test.hub.product.domain.Attribute;

public interface IAttributeValueRepository {
    Attribute findById(Long id);
}
