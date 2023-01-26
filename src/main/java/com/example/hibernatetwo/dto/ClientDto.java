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
public class ClientDto {
  private Integer id;
  private String name;
  private String email;
  private String phone;
}
