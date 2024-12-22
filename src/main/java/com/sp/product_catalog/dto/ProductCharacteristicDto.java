package com.sp.product_catalog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductCharacteristicDto {
    @NotBlank(message = "name shouldn't be null, blank and whitespace!")
    private String name;
    @NotBlank(message = "value shouldn't be null, blank and whitespace!")
    private String value;
}
