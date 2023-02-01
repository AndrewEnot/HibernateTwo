package com.example.hibernatetwo.repository;

import com.example.hibernatetwo.model.OrderItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

/*
 * @author Oksiuta Andrii
 * 01.02.2023
 * 13:42
 */
@Repository
@Slf4j
public class CustomOrderItemRepositoryImpl implements CustomOrderItemRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public List<OrderItem> findByOrderId(int id) {
    List<OrderItem> resultList;
    Query query = entityManager
        .createQuery("select o from OrderItem o where o.order = '" + id + "'");
    resultList = query.getResultList();
    if (resultList.isEmpty()) {
      throw new EntityNotFoundException("No orderItem with such order ID!");
    }
    return resultList;
  }
}
