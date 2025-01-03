package vn.test.hub.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.test.hub.product.domain.Category;
import vn.test.hub.product.dto.response.APIResponse;
import vn.test.hub.product.service.ICategoryService;

import java.util.List;

@RequestMapping("/api/v1/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Category>>> getCategories() {
        return ResponseEntity.ok(
                APIResponse.<List<Category>>builder()
                        .status(1)
                        .message("Success")
                        .data(categoryService.findAll())
                        .build()
        );
    }
}
