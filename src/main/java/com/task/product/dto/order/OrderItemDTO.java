package com.task.product.dto.order;

import com.task.product.dto.product.ProductDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class OrderItemDTO {

    protected ProductDTO productDTO;

    protected Long quantity;

    protected BigDecimal price;

}
