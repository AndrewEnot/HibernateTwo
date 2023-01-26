package com.example.hibernatetwo.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 17:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientOrdersDto {
  private Integer id;
  private String name;
  private String email;
  private String phone;
  private AddressDto address;
  private List<OrderDto> ordersHistory;
}
