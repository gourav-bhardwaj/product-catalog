package com.sp.product_catalog.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    @NotEmpty(message = "categoryName should not be empty!")
    private String categoryName;
}
