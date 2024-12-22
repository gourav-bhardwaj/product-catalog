package com.sp.product_catalog.util;

import com.sp.product_catalog.dto.CategoryRequest;
import com.sp.product_catalog.dto.CategoryResponse;
import com.sp.product_catalog.dto.ProductRequest;
import com.sp.product_catalog.dto.ProductResponse;
import com.sp.product_catalog.model.Category;
import com.sp.product_catalog.model.Product;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class ProductMapper implements Mapper<Product, ProductRequest, ProductResponse> {

    @Override
    public Product convertToModel(ProductRequest request) {
        return new Product(request.getName(),
                request.getPrice(),
                request.getDiscount(),
                new Category(UUID.fromString(request.getCategoryId())),
                request.getPreviewImage());
    }

    @Override
    public ProductResponse convertToDto(Product model) {
        return ProductResponse.builder()
                .productId(model.getId().toString())
                .name(model.getName())
                .price(model.getPrice())
                .discount(model.getDiscount())
                .categoryDto(new CategoryResponse(model.getCategory().getId().toString(),
                        model.getCategory().getCategoryName()))
                .previewImage(model.getPreviewImage())
                .build();
    }

}
