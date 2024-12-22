package com.sp.product_catalog.service;

import com.sp.product_catalog.dto.ProductDetailDto;

public interface ProductDetailService {

    ProductDetailDto saveProductDetail(ProductDetailDto productDetailRequest);
    ProductDetailDto updateProductDetail(String id, ProductDetailDto productDetailRequest);
    ProductDetailDto getProductDetail(String id);
    void deleteProductDetail(String id);

}
