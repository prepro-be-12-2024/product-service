package vn.test.hub.product.repo;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.test.hub.core.searching.FilterParam;
import vn.test.hub.core.utils.SearchingUtils;
import vn.test.hub.product.datasource.entity.AttributeEntity;
import vn.test.hub.product.datasource.entity.AttributeValueEntity;
import vn.test.hub.product.datasource.entity.ProductEntity;
import vn.test.hub.product.datasource.repo.IJPAAttributeRepository;
import vn.test.hub.product.datasource.repo.IJPAAttributeValueRepository;
import vn.test.hub.product.datasource.repo.IJPAProductRepository;
import vn.test.hub.product.domain.Product;
import vn.test.hub.product.mapper.IProductMapper;
import vn.test.hub.product.repository.IProductRepository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class ProductRepository implements IProductRepository {

    private final IProductMapper productMapper;
    private final IJPAProductRepository jpaProductRepository;
    private final IJPAAttributeRepository attributeRepository;
    private final IJPAAttributeValueRepository attributeValueRepository;

    private Product map(ProductEntity entity, List<AttributeValueEntity> attributeValues) {
        Product productDTO = productMapper.toDTO(entity);
        Map<String, String> attributes = new HashMap<>();

        for (AttributeValueEntity av : attributeValues) {
            if (av.getProductId().equals(entity.getId()) && Boolean.FALSE.equals(av.getDeleted())) {
                attributeRepository.findByIdAndDeleted(av.getAttributeId(), false)
                        .ifPresent(attributeEntity -> attributes.put(attributeEntity.getName(), av.getValue()));
            }
        }
        productDTO.setAttributes(attributes);

        return productDTO;
    }

    private Specification<ProductEntity> filterByPrice(List<FilterParam> filterParams) {
        FilterParam minPriceFilterParam = null;
        FilterParam maxPriceFilterParam = null;

        for (FilterParam param : filterParams) {
            if (param.getFieldName().equals("min_price")) {
                minPriceFilterParam = param;
            }
            if (param.getFieldName().equals("max_price")) {
                maxPriceFilterParam = param;
            }
        }

        if (minPriceFilterParam != null || maxPriceFilterParam != null) {
            FilterParam minPriceParam = minPriceFilterParam;
            FilterParam maxPriceParam = maxPriceFilterParam;
            return (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                assert query != null;
                Root<AttributeValueEntity> attributeValueRoot = query.from(AttributeValueEntity.class);
                Root<AttributeEntity> attributeRoot = query.from(AttributeEntity.class);

                predicates.add(criteriaBuilder.equal(
                        attributeValueRoot.get("productId"), root.get("id")
                ));

                predicates.add(criteriaBuilder.equal(
                        attributeValueRoot.get("attributeId"), attributeRoot.get("id")
                ));

                predicates.add(criteriaBuilder.equal(attributeRoot.get("name"), "price"));

                if (minPriceParam != null)
                    predicates.add(criteriaBuilder.ge(attributeValueRoot.get("value").as(Float.class), (Float) minPriceParam.getValue()));

                if (maxPriceParam != null)
                    predicates.add(criteriaBuilder.le(attributeValueRoot.get("value").as(Float.class), (Float) maxPriceParam.getValue()));

                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
        } else {
            return null;
        }
    }

    @Override
    public Page<Product> findAll(List<FilterParam> filterParams, Pageable pageable) {
        List<FilterParam> priceFilterParams = new ArrayList<>();
        List<FilterParam> columnFilterParams = new ArrayList<>();

        for (FilterParam param : filterParams) {
            if (param.getFieldName().equals("min_price") || param.getFieldName().equals("max_price")) {
                priceFilterParams.add(param);
            } else
                columnFilterParams.add(param);
        }

        Specification<ProductEntity> specification = SearchingUtils.<ProductEntity>findBy(columnFilterParams).and(filterByPrice(priceFilterParams));

        Page<ProductEntity> productEntities = jpaProductRepository.findAll(specification, pageable);
        return productEntities.map(entity -> {
                    List<AttributeValueEntity> valueEntities = attributeValueRepository.findAllByProductId(entity.getId());
                    return map(entity, valueEntities);
                }
        );
    }

    @Override
    public List<Product> findByIds(List<String> productIds) {
        List<ProductEntity> productEntities = jpaProductRepository.findAllById(productIds);
        List<AttributeValueEntity> attributeValues = attributeValueRepository.findAllByProductIdIn(productIds);

        return productEntities.stream().map(entity -> map(entity, attributeValues)).toList();
    }

    @Override
    public Product findById(String id) {
        ProductEntity productEntity = jpaProductRepository.findById(id).orElse(null);
        if (productEntity != null) {
            List<AttributeValueEntity> valueEntities = attributeValueRepository.findAllByProductId(id);
            return map(productEntity, valueEntities);
        }
        return null;
    }

    @Override
    @Transactional
    public Product save(Product product) {
        ProductEntity entity = productMapper.toEntity(product);
        ProductEntity savedProduct = jpaProductRepository.saveAndFlush(entity);

        /*TODO:
         * ...
         * jpaAttributeValue.save
         * jpaGuide.save
         * jpaImage.save
         * */

        return productMapper.toDTO(savedProduct);
    }

    @Override
    public Product update(Product product) {
        ProductEntity entity = productMapper.toEntity(product);
        ProductEntity savedProduct = jpaProductRepository.save(entity);

        /*TODO:
         * ...
         * jpaAttributeValue.save
         * jpaGuide.save
         * jpaImage.save
         * */

        return productMapper.toDTO(savedProduct);
    }

    @Override
    public Product delete(String id) {
        ProductEntity entity = jpaProductRepository.findById(id).orElse(null);
        if (entity != null) {
            entity.setDeleted(true);
            ProductEntity productEntity = jpaProductRepository.save(entity);
            return productMapper.toDTO(productEntity);
        }
        return null;
    }
}
