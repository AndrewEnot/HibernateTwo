package com.example.hibernatetwo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 14:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

  private Integer id;
  private ClientDto client;
  private String country;
  private String city;
  private String street;
  private String house;
}
