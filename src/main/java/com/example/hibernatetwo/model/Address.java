package com.example.hibernatetwo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 14:58
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "my_shop")
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @OneToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "fk_client_id", referencedColumnName = "id")
  private Client client;
  private String country;
  private String city;
  private String street;
  private String house;
}
