package com.task.product.controller;

import com.task.product.dto.order.OrderAddDTO;
import com.task.product.dto.order.OrderDTO;
import com.task.product.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "add")
    @Operation(summary = "Create an order")
    public OrderDTO createOrder(@RequestBody final OrderAddDTO orderAddDTO) {
        return orderService.createOrder(orderAddDTO);
    }

    @GetMapping()
    @Operation(summary = "Retrieve orders between a range")
    public List<OrderDTO> retrieveOrders(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime start,
                                         @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime end) {
        return orderService.fetchOrdersByDateRange(start, end);
    }

}
