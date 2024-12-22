package com.sp.product_catalog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    @NotEmpty(message = "name should not be empty!")
    private String name;

    @NotNull(message = "price should not be empty!")
    private Double price;

    @Range(min = 5, max = 80, message = "discount should be in between 5% to 80%")
    @NotNull(message = "discount should not be empty!")
    private Double discount;

    @NotEmpty(message = "categoryId should not be empty!")
    private String categoryId;

    @Pattern(
            regexp = "^file://\\w+/\\w+[.](png|jpeg|gif)$",
            message = "previewImage should match the pattern file://<path>/<filename>.(png|jpeg|gif)"
    )
    @NotEmpty(message = "previewImage should not be empty!")
    private String previewImage;
}
