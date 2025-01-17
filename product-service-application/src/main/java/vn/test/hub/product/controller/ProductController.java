package vn.test.hub.product.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.test.hub.core.info.PaginationInfo;
import vn.test.hub.core.pagination.PaginationMapper;
import vn.test.hub.core.pagination.PaginationRequest;
import vn.test.hub.core.response.BaseResponse;
import vn.test.hub.core.utils.ResponseUtils;
import vn.test.hub.core.validator.anotation.IdConstraint;
import vn.test.hub.product.constant.SystemConstant;
import vn.test.hub.product.domain.Product;
import vn.test.hub.product.dto.request.product.ProductCreationRequest;
import vn.test.hub.product.dto.request.product.ProductRequest;
import vn.test.hub.product.dto.request.product.ProductUpdateRequest;
import vn.test.hub.product.mapper.ProductMapper;
import vn.test.hub.product.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {
    private final ProductMapper productMapper;
    private final IProductService productService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<Product>, PaginationInfo>> getAllProduct(@Valid @ModelAttribute ProductRequest req) throws IllegalAccessException {
        Product product = productMapper.toDTO(req);
        PaginationRequest paginationRequest = new PaginationRequest(req.getSort(), req.getPage(), req.getLimit());

        Page<Product> products = productService.findAll(product, paginationRequest);
        PaginationInfo paginationInfo = PaginationMapper.toPaginationInfo(paginationRequest, products.getTotalPages());

        return ResponseUtils.success(products.getContent(), paginationInfo);
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Product, Void>> create(@RequestBody @Valid ProductCreationRequest req) {
        Product product = productMapper.toDTO(req);
        return ResponseUtils.success(productService.save(product), null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Product, Void>> getProduct(@NotBlank(message = SystemConstant.ID_NOT_BLANK)
                                                                  @IdConstraint
                                                                  @PathVariable String id) {
        return ResponseUtils.success(productService.findById(id), null);
    }

    @PostMapping("/ids")
    public ResponseEntity<BaseResponse<List<Product>, Void>> getProducts(@RequestBody List<@NotBlank(message = SystemConstant.ID_NOT_BLANK) @IdConstraint String> ids) {
        return ResponseUtils.success(productService.findByIds(ids), null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<Product, Void>> updateProduct(@NotBlank(message = SystemConstant.ID_NOT_BLANK) @IdConstraint @PathVariable("id") String id,
                                                                           @RequestBody @Valid ProductUpdateRequest req) {
        Product product = productMapper.toDTO(req);
        product.setId(id);
        return ResponseUtils.success(productService.update(product), null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Product, Void>> updateProduct(@NotBlank(message = SystemConstant.ID_NOT_BLANK) @IdConstraint @PathVariable("id") String id) {
        return ResponseUtils.success(productService.delete(id), null);
    }
}
