package com.task.product.mapper.order;

import com.task.product.mapper.product.ProductMapper;
import com.task.product.dto.order.OrderDTO;
import com.task.product.dto.order.OrderItemDTO;
import com.task.product.model.Order;
import com.task.product.model.OrderItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final ProductMapper productMapper;

    public OrderMapper(final ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public OrderItemDTO toOrderItemDTO(final OrderItem orderItem) {
        return new OrderItemDTO()
                .setPrice(orderItem.getPrice())
                .setQuantity(orderItem.getQuantity())
                .setProductDTO(productMapper.toProductDTO(orderItem.getProduct()));
    }

    public List<OrderItemDTO> toOrderItemDTOs(final List<OrderItem> orderItems) {
        return orderItems.stream().map(this::toOrderItemDTO).collect(Collectors.toList());
    }

    public OrderDTO toOrderDTO(final Order order, final List<OrderItem> orderItems) {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item: orderItems) {
            total = total.add(item.getPrice());
        }

        return new OrderDTO()
                .setId(order.getId())
                .setEmail(order.getEmail())
                .setTotalPrice(total)
                .setOrderItems(toOrderItemDTOs(orderItems));
    }
}
