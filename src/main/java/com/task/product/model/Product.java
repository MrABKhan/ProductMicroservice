package com.task.product.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    private Long availableQuantity;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Product(final String name, final BigDecimal price, final Long availableQuantity) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;

        LocalDateTime currentDateTime = LocalDateTime.now();
        this.createdAt = currentDateTime;
        this.updatedAt = currentDateTime;
    }

}
