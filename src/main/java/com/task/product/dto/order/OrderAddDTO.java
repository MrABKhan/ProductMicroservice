package com.task.product.dto.order;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class OrderAddDTO {

    private String email;

    private List<OrderItemAddDTO> orderItems;

}
