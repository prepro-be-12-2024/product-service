package vn.test.hub.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.test.hub.core.exception.BadRequestException;
import vn.test.hub.core.exception.NotFoundException;
import vn.test.hub.core.pagination.PaginationMapper;
import vn.test.hub.core.pagination.PaginationRequest;
import vn.test.hub.core.searching.FilterParam;
import vn.test.hub.core.searching.Operators;
import vn.test.hub.product.constant.SystemConstant;
import vn.test.hub.product.domain.Product;
import vn.test.hub.product.repository.IProductRepository;
import vn.test.hub.product.service.IProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private static final String PRODUCT = "Product";
    private final IProductRepository productRepository;

    @Override
    public Page<Product> findAll(Product product, PaginationRequest paginationRequest) {
        List<FilterParam> filterParams = new ArrayList<>();
        if (product.getName() != null) {
            filterParams.add(FilterParam.builder()
                    .fieldName("name")
                    .value(product.getName())
                    .operator(Operators.LIKE).build());
        }

        if (product.getUpdatedBy() != null) {
            filterParams.add(FilterParam.builder()
                    .fieldName(SystemConstant.CREATED_BY_COLUMN_NAME)
                    .value(product.getUpdatedBy())
                    .operator(Operators.EQ).build());
        }

        if (product.getMinPrice() != null) {
            filterParams.add(FilterParam.builder()
                    .fieldName("min_price")
                    .value(product.getMinPrice())
                    .operator(Operators.GE).build());
        }

        if (product.getMaxPrice() != null) {
            filterParams.add(FilterParam.builder()
                    .fieldName("max_price")
                    .value(product.getMaxPrice())
                    .operator(Operators.LE).build());
        }

        boolean deleted = product.getDeleted() != null && product.getDeleted();
        filterParams.add(
                FilterParam.builder()
                        .fieldName(SystemConstant.DELETED_COLUMN_NAME)
                        .value(deleted)
                        .operator(Operators.EQ).build()
        );

        List<String> validSortColumns = Arrays.asList("name", SystemConstant.CREATED_BY_COLUMN_NAME, "price");
        Pageable pageable = PaginationMapper.toPageable(paginationRequest, validSortColumns);

        return productRepository.findAll(filterParams, pageable);
    }

    @Override
    public List<Product> findByIds(List<String> ids) {
        List<Product> products = productRepository.findByIds(ids);
        List<String> notFoundIds = new ArrayList<>();

        if (products.size() != ids.size()) {
            for (Product product : products) {
                if (!ids.contains(product.getId())) { // check product id contain ids
                    notFoundIds.add(product.getId());
                }
            }
            throw new BadRequestException("Not Found ids: " + String.join(",", notFoundIds), 4000);
        } else
            return productRepository.findByIds(ids);
    }

    @Override
    public Product findById(String id) {
        Product product = productRepository.findById(id);
        if (product == null)
            throw new NotFoundException(String.format(SystemConstant.NOT_FOUND,PRODUCT, id), 4000);
        return product;
    }

    @Override
    public Product save(Product product) {
        if (product.getId() != null) {
            throw new BadRequestException("Id not required", 5000);
        }
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        String id = product.getId();
        if (productRepository.findById(id) == null) {
            throw new NotFoundException(String.format(SystemConstant.NOT_FOUND,PRODUCT, id), 4000);
        }

        return productRepository.update(product);
    }

    @Override
    public Product delete(String id) {
        Product product = productRepository.delete(id);
        if (product == null)
            throw new NotFoundException(String.format(SystemConstant.NOT_FOUND,PRODUCT, id), 4000);
        else {
            product.setDeleted(true);
            return productRepository.save(product);
        }
    }

}
