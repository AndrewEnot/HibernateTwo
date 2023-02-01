package com.example.hibernatetwo.repository;

import com.example.hibernatetwo.model.OrderItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 15:15
 */
@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer>,
    CustomOrderItemRepository {
}
