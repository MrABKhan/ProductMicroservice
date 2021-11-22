package com.task.product.dto.order;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class OrderItemAddDTO {

    private Long productId;

    private Long quantity;

}
