package com.example.hibernatetwo.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 14:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
  private Integer id;
  private ClientDto client;
  private List<OrderItemDto> orderItems; //(OneToMany)//
  private double sumOfOrder;

}
