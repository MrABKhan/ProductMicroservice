package com.task.product.service;

import com.task.product.dto.order.OrderAddDTO;
import com.task.product.dto.order.OrderDTO;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface OrderService {

    /**
     * Takes an {@link OrderAddDTO}, saves an {@link com.task.product.model.Order} and a {@link List<com.task.product.model.OrderItem>}
     * separtely into the database, the latest price is fetched from the database and the ordered quantity is also validated.
     *
     * @param orderAddDTO {@link OrderAddDTO}
     * @return {@link OrderDTO}
     */
    OrderDTO createOrder(final OrderAddDTO orderAddDTO);

    List<OrderDTO> fetchOrdersByDateRange(final LocalDateTime startDate, final LocalDateTime endDateTime);
}
