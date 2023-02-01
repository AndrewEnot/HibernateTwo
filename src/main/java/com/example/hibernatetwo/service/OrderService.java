package com.example.hibernatetwo.service;

import com.example.hibernatetwo.dto.OrderDto;
import com.example.hibernatetwo.dto.OrderItemDto;
import com.example.hibernatetwo.model.Order;
import com.example.hibernatetwo.model.OrderItem;
import com.example.hibernatetwo.repository.OrderItemRepository;
import com.example.hibernatetwo.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 15:50
 */
@Service
@Data
@Slf4j
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderItemRepository orderItemRepository;
  private final ObjectMapper objectMapper;
  private final ProductService productService;


  public OrderDto createOrder(OrderDto orderDto) {
    if (orderDto == null) {
      log.info("INPUTTED DATA with null!!! ");
      return new OrderDto();
    }
    var order = objectMapper.convertValue(orderDto, Order.class);
    orderRepository.save(order);
    orderDto.setId(order.getId());
    return orderDto;
  }


  public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
    if (orderItemDto == null) {
      log.info("INPUTTED DATA with null!!! ");
      return new OrderItemDto();
    }
    var orderItem = objectMapper.convertValue(orderItemDto, OrderItem.class);
    orderItemRepository.save(orderItem);
    orderItemDto.setId(orderItem.getId());
    return orderItemDto;
  }

  public List<OrderDto> getOrderDtosByClientId(int id) {
    List<OrderDto> resultList = new ArrayList<>();
    var orders = orderRepository.findOrdersByClientId(id);
    orders.forEach(order -> resultList.add(objectMapper.convertValue(order, OrderDto.class)));
    return resultList;
  }

  public List<OrderItemDto> getOrderItemsByOrderId(int id) {
    List<OrderItemDto> resultList = new ArrayList<>();
    var orderItems = orderItemRepository.findByOrderId(id);
    orderItems.forEach(
        orderItem -> resultList.add(objectMapper.convertValue(orderItem, OrderItemDto.class)));
    return resultList;
  }

  public List<Integer> getOrderIdsByClientId(int id) {
    return orderRepository.findOrderIdByClientId(id);
  }

  public OrderDto addOrderItem(OrderDto orderDto, int productId, int quantity) {
    var orderItemDto = new OrderItemDto();
    var productDto = productService.findById(productId);

    orderDto.setSumOfOrder(orderDto.getSumOfOrder() + (productDto.getPrice() * quantity));
    orderDto = createOrder(orderDto);

    orderItemDto.setOrder(orderDto);
    orderItemDto.setProduct(productDto);
    orderItemDto.setCount(quantity);
    createOrderItem(orderItemDto);

    return orderDto;
  }
}
