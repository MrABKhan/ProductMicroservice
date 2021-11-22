package com.task.product.dto.product;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class ProductAddDTO {

    private String name;

    private BigDecimal price;

    private Long availableQuantity;

}
