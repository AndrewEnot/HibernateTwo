package com.example.hibernatetwo.repository;

import com.example.hibernatetwo.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 15:14
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

}
