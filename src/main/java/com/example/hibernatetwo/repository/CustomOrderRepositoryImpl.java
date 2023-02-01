package com.example.hibernatetwo.repository;

import com.example.hibernatetwo.model.Order;
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
 * 12:50
 */
@Repository
@Slf4j
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  @Transactional
  public List<Order> findOrdersByClientId(int id) {
    List<Order> resultList;
    Query query = entityManager
        .createQuery("select o from Order o where o.client = '" + id + "'");
    resultList = query.getResultList();
    if (resultList.isEmpty()) {
      throw new EntityNotFoundException("No order with such ID!");
    }
    return resultList;
  }

  @Override
  public List<Integer> findOrderIdByClientId(int id) {
    List<Integer> resultList;
    Query query = entityManager
        .createQuery("select o.id from Order o where o.client = '" + id + "'");
    resultList = query.getResultList();
    if (resultList.isEmpty()) {
      throw new EntityNotFoundException("No order with such ID!");
    }
    return resultList;
  }
}
