package com.sp.product_catalog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sp.product_catalog.dto.CategoryRequest;
import com.sp.product_catalog.dto.CategoryResponse;
import com.sp.product_catalog.service.CategoryService;
import com.sp.product_catalog.util.Utility;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;
    private final Utility utility;

    public CategoryController(CategoryService service, Utility utility) {
        this.service = service;
        this.utility = utility;
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> saveCategoryEndpoint(@RequestBody @Valid CategoryRequest categoryRequest) throws JsonProcessingException {
        log.info("Category creation endpoint invoked");
        CategoryRequest sanitizedRequestBody = utility.sanitizeRequestBody(categoryRequest, categoryRequest.getClass());
        CategoryResponse categoryResponse = service.saveCategory(sanitizedRequestBody);
        return new ResponseEntity<>(categoryResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}/update")
    public ResponseEntity<CategoryResponse> updateCategoryEndpoint(@PathVariable String categoryId,
                                                                   @RequestBody @Valid CategoryRequest categoryRequest) throws JsonProcessingException {
        log.info("Category modification endpoint invoked");
        CategoryRequest sanitizedRequestBody = utility.sanitizeRequestBody(categoryRequest, CategoryRequest.class);
        String sanitizedCategoryId = utility.sanitizeObject(categoryId, String.class);
        CategoryResponse categoryResponse = service.updateCategory(sanitizedCategoryId, sanitizedRequestBody);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> deleteCategoryEndpoint(@PathVariable String categoryId) {
        log.info("Category deletion endpoint invoked");
        String sanitizedCategoryId = utility.sanitizeObject(categoryId, String.class);
        CategoryResponse categoryResponse = service.deleteCategory(sanitizedCategoryId);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategoriesEndpoint() {
        log.info("Fetch all category endpoint invoked");
        List<CategoryResponse> categories = service.getCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoryEndpoint(@PathVariable String categoryId) {
        log.info("Category fetch endpoint invoked");
        String sanitizedCategoryId = utility.sanitizeObject(categoryId, String.class);
        CategoryResponse category = service.getCategory(sanitizedCategoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
