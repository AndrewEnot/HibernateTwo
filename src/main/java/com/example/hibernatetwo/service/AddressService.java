package com.example.hibernatetwo.service;

import com.example.hibernatetwo.dto.AddressDto;
import com.example.hibernatetwo.model.Address;
import com.example.hibernatetwo.repository.AddressRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 * @author Oksiuta Andrii
 * 25.01.2023
 * 11:02
 */
@Service
@Data
@Slf4j
public class AddressService {

  private final AddressRepository addressRepository;
  private final ObjectMapper objectMapper;

  public AddressDto addAddress(AddressDto addressDto) {
    if (addressDto == null) {
      log.info("INPUTTED DATA with null!!! ");
      return new AddressDto();
    }
    var address = objectMapper.convertValue(addressDto, Address.class);
    addressRepository.save(address);
    addressDto.setId(address.getId());
    return addressDto;
  }

  public AddressDto findByClientID(int clientId) {
    for (Address address : addressRepository.findAll()) {
      if (address.getClient().getId() == clientId) {
        return objectMapper.convertValue(address, AddressDto.class);
      }
    }
    throw new EntityNotFoundException("No Client with such ID!!!");
  }
}
