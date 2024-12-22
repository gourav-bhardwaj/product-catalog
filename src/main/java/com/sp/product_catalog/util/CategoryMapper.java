package com.sp.product_catalog.util;

import com.sp.product_catalog.dto.CategoryRequest;
import com.sp.product_catalog.dto.CategoryResponse;
import com.sp.product_catalog.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements Mapper<Category, CategoryRequest, CategoryResponse> {

    @Override
    public Category convertToModel(CategoryRequest request) {
        return new Category(request.getCategoryName());
    }

    @Override
    public CategoryResponse convertToDto(Category model) {
        return new CategoryResponse(model.getId().toString(), model.getCategoryName());
    }

}
