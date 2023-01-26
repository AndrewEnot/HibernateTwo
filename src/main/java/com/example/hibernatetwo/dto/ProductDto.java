package com.example.hibernatetwo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 14:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
  private Integer id;
  private String name;
  private String description;
  private double price;
}
