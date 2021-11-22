package com.task.product.repository;

import com.task.product.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrder_Id(final Long id);

}
