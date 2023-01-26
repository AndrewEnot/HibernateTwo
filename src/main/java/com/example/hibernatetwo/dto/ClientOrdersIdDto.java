package com.example.hibernatetwo.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author Oksiuta Andrii
 * 25.01.2023
 * 13:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientOrdersIdDto {
  private Integer id;
  private String name;
  private String email;
  private String phone;
  private List<Integer> orderIds;
}
