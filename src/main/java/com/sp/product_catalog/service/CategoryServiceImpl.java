package com.sp.product_catalog.service;

import com.sp.product_catalog.dto.CategoryRequest;
import com.sp.product_catalog.dto.CategoryResponse;
import com.sp.product_catalog.model.Category;
import com.sp.product_catalog.repository.CategoryRepository;
import com.sp.product_catalog.util.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = repository.save(mapper.convertToModel(categoryRequest));
        log.info("Product created successfully");
        return mapper.convertToDto(category);
    }

    @Override
    public CategoryResponse updateCategory(String categoryId, CategoryRequest categoryRequest) {
        Category category = repository.findById(UUID.fromString(categoryId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product not found for update!"));
        category.setCategoryName(categoryRequest.getCategoryName());
        category = repository.save(category);
        log.info("Product updated successfully");
        return mapper.convertToDto(category);
    }

    @Override
    public List<CategoryResponse> getCategories() {
        return repository.findAll().stream().map(mapper::convertToDto).toList();
    }

    @Override
    public CategoryResponse getCategory(String categoryId) {
        return repository.findById(UUID.fromString(categoryId))
                .map(mapper::convertToDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));
    }

    @Override
    public CategoryResponse deleteCategory(String categoryId) {
        Optional<Category> categoryOpt = repository.findById(UUID.fromString(categoryId));
        if (categoryOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found for deletion!");
        }
        return mapper.convertToDto(categoryOpt.get());
    }

}
