package com.sp.product_catalog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class Product extends BaseModel {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "product_id")
    private UUID id;
    private String name;
    private Double price;
    private Double discount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
    private String previewImage;

    public Product(String name, Double price, Double discount, Category category, String previewImage) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.previewImage = previewImage;
    }

}
