package com.sp.product_catalog.service;

import com.sp.product_catalog.dto.ProductRequest;
import com.sp.product_catalog.dto.ProductResponse;
import com.sp.product_catalog.dto.ProductResponsePage;

public interface ProductService {

    ProductResponse saveProduct(ProductRequest product);
    ProductResponse updateProduct(String productId, ProductRequest product);
    ProductResponsePage getProductWithPagination(Integer offset, Integer limit, String categoryId);
    ProductResponse deleteProduct(String productId);

}
