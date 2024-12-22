package com.sp.product_catalog.dto;

import com.sp.product_catalog.common.ProductMediaType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductMediaDto {
    private String url;
    private ProductMediaType mediaType;
    private Double width;
    private Double height;
}
