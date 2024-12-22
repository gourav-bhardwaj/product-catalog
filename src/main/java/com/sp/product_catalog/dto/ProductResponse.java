package com.sp.product_catalog.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductResponse {
    private String productId;
    private String name;
    private Double price;
    private Double discount;
    private CategoryResponse categoryDto;
    private String previewImage;
}
