package com.example.hibernatetwo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 17:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientAddressDto {
  private AddressDto address;
  private Integer id;
  private String name;
  private String email;
  private String phone;

}
