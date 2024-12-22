package com.sp.product_catalog.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class ProductResponsePage {
    private List<ProductResponse> products;
    private long totalElements;
    private  int page;
    private int size;
}
