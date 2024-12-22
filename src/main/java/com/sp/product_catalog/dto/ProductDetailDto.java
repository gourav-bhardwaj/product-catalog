package com.sp.product_catalog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDetailDto {

    @NotBlank(message = "id shouldn't be null, blank and whitespace!")
    private String id;

    @NotEmpty(message = "mediaList shouldn't be null or empty!")
    private List<ProductMediaDto> mediaList;

    @NotBlank(message = "description shouldn't be null, blank and whitespace!")
    private String description;

    @NotNull(message = "characteristics shouldn't be null")
    private List<ProductCharacteristicDto> characteristics;

    @NotBlank(message = "ingredient shouldn't be null, blank and whitespace!")
    private String ingredient;

    @NotBlank(message = "warningNote shouldn't be null, blank and whitespace!")
    private String warningNote;
}
