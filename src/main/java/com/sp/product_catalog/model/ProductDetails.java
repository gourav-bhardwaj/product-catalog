package com.sp.product_catalog.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document("product_details")
public class ProductDetails {
    @Id
    private String id;
    private List<String> images;
    private String description;
    private List<ProductMedia> mediaList;
    private List<ProductCharacteristic> characteristic;
    private String ingredient;
    private String warningNote;
}
