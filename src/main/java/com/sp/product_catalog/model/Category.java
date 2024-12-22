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
@Table(name = "category")
@NoArgsConstructor
public class Category extends BaseModel {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "category_id")
    private UUID id;
    private String categoryName;

    public Category(UUID id) {
        this.id = id;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }



}
