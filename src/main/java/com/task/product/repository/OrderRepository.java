package com.task.product.repository;

import com.task.product.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}
