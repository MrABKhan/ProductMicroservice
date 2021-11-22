package com.task.product.dto.order;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class OrderDTO {

    private Long id;

    private String email;

    private List<OrderItemDTO> orderItems;

    private BigDecimal totalPrice;

}
