package com.example.hibernatetwo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 14:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
  private Integer id;
  private OrderDto order;
  private ProductDto product; //(ManyToOne)//
  private int count;
}
