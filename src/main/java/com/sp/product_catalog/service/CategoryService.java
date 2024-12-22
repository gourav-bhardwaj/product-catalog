package com.sp.product_catalog.service;

import com.sp.product_catalog.dto.*;
import java.util.List;

public interface CategoryService {

    CategoryResponse saveCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(String categoryId, CategoryRequest categoryRequest);
    List<CategoryResponse> getCategories();
    CategoryResponse getCategory(String categoryId);
    CategoryResponse deleteCategory(String categoryId);

}
