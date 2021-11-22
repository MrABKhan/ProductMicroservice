package com.task.product.service;

import com.task.product.dto.order.OrderAddDTO;
import com.task.product.dto.order.OrderDTO;
import com.task.product.dto.order.OrderItemAddDTO;
import com.task.product.mapper.order.OrderMapper;
import com.task.product.model.Order;
import com.task.product.model.OrderItem;
import com.task.product.model.Product;
import com.task.product.repository.OrderItemRepository;
import com.task.product.repository.OrderRepository;
import com.task.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(final OrderMapper orderMapper,
                            final OrderRepository orderRepository,
                            final ProductRepository productRepository,
                            final OrderItemRepository orderItemRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public OrderDTO createOrder(final OrderAddDTO orderAddDTO) {
        final List<OrderItemAddDTO> orderItemDTOs = orderAddDTO.getOrderItems();

        // persist order
        final Order order = new Order(orderAddDTO.getEmail());
        final Order savedOrder = orderRepository.save(order);

        final List<OrderItem> orderItems = orderItemDTOs.stream().map((orderItemDTO) -> {
            Product product = productRepository.findById(orderItemDTO.getProductId()).orElseThrow(() -> new EntityNotFoundException("Product not found"));

            if (product.getAvailableQuantity() < orderItemDTO.getQuantity()) {
                throw new IllegalStateException("Ordered quantity not available");
            }

            BigDecimal totalPrice = product.getPrice().multiply(BigDecimal.valueOf(orderItemDTO.getQuantity()));

            return new OrderItem(savedOrder, product, orderItemDTO.getQuantity(), totalPrice);
        }).collect(Collectors.toList());

        final List<OrderItem> savedOrderItems = Streamable.of(orderItemRepository.saveAll(orderItems)).toList();

        return orderMapper.toOrderDTO(savedOrder, savedOrderItems);
    }

    @Override
    public List<OrderDTO> fetchOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDateTime) {
        List<Order> orders = orderRepository.findAllByCreatedAtBetween(startDate, endDateTime);

        List<OrderDTO> orderDTOS = new ArrayList<>();

        orders.forEach((order) -> {
            List<OrderItem> orderItems = orderItemRepository.findAllByOrder_Id(order.getId());

            orderDTOS.add(orderMapper.toOrderDTO(order, orderItems));
        });

        return orderDTOS;
    }
}
