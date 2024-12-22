package com.sp.product_catalog.common;

import lombok.Getter;

@Getter
public enum ProductMediaType {
    IMAGE("Image", 1), VIDEO("Video", 2);

    private final String name;
    private final Integer value;

    ProductMediaType(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

}
