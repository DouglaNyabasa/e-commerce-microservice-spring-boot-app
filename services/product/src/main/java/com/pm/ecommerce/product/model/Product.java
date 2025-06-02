package com.pm.ecommerce.product.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private double availableQuantity;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
