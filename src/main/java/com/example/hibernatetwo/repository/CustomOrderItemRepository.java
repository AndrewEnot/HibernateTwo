package com.example.hibernatetwo.repository;

import com.example.hibernatetwo.model.OrderItem;
import java.util.List;
import org.springframework.stereotype.Repository;

/*
 * @author Oksiuta Andrii
 * 01.02.2023
 * 13:41
 */
@Repository
public interface CustomOrderItemRepository {

  List<OrderItem> findByOrderId(int id);

}
