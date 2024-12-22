package com.sp.product_catalog.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private String categoryId;
    private String categoryName;
}
