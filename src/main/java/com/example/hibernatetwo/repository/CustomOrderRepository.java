package com.example.hibernatetwo.repository;

import com.example.hibernatetwo.model.Order;
import java.util.List;
import org.springframework.stereotype.Repository;

/*
 * @author Oksiuta Andrii
 * 01.02.2023
 * 12:49
 */
@Repository
public interface CustomOrderRepository {

  List<Order> findOrdersByClientId(int id);

  List<Integer> findOrderIdByClientId(int id);
}
