package com.example.hibernatetwo.service;

import com.example.hibernatetwo.dto.AddressDto;
import com.example.hibernatetwo.dto.ClientAddressDto;
import com.example.hibernatetwo.dto.ClientDto;
import com.example.hibernatetwo.dto.ClientOrdersDto;
import com.example.hibernatetwo.dto.ClientOrdersIdDto;
import com.example.hibernatetwo.dto.OrderDto;
import com.example.hibernatetwo.dto.OrderItemDto;
import com.example.hibernatetwo.model.Client;
import com.example.hibernatetwo.model.Order;
import com.example.hibernatetwo.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 15:50
 */
@Service
@Data
@Slf4j
public class ClientService {

  private final ClientRepository clientRepository;
  private final ObjectMapper objectMapper;

  public ClientDto createClient(ClientDto clientDto) {
    if (clientDto == null) {
      log.info("INPUTTED DATA with null!!! ");
      return new ClientDto();
    }
    var client = objectMapper.convertValue(clientDto, Client.class);
    clientRepository.save(client);
    clientDto.setId(client.getId());
    return clientDto;
  }

  public ClientDto findById(int id) {
    var byId = clientRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("No Client with such ID!"));
    return objectMapper.convertValue(byId, ClientDto.class);
  }

  public ClientOrdersDto getFullInfoById(int id) {
    var byId = clientRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("No Client with such ID!"));
    var address = objectMapper.convertValue(byId.getAddress(), AddressDto.class);
    var clientOrdersDto = objectMapper.convertValue(byId, ClientOrdersDto.class);
    var ordersHistory = byId.getOrdersHistory();
    List<OrderDto> orderDtoList = new ArrayList<>();
    List<OrderItemDto> orderItemDtoList = new ArrayList<>();

    ordersHistory.forEach(order -> {
      var orderDto = objectMapper.convertValue(order, OrderDto.class);
      order.getOrderItems()
          .forEach(item -> orderItemDtoList
              .add(objectMapper.convertValue(item, OrderItemDto.class)));
      orderDto.setOrderItems(orderItemDtoList);
      orderDtoList.add(orderDto);
    });

    clientOrdersDto.setAddress(address);
    clientOrdersDto.setOrdersHistory(orderDtoList);

    return clientOrdersDto;
  }

  public ClientAddressDto getShortInfoById(int id) {
    var byId = clientRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("No Client with such ID!"));
    var clientAddressDto = objectMapper.convertValue(byId, ClientAddressDto.class);
    var address = objectMapper.convertValue(byId.getAddress(), AddressDto.class);
    clientAddressDto.setAddress(address);
    return clientAddressDto;
  }

  public ClientOrdersIdDto getClientInfoWithOrderIdsById(int id) {
    var clientById = clientRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("No Client with such ID!"));

    var clientOrdersIdDto = objectMapper.convertValue(clientById, ClientOrdersIdDto.class);
    List<Integer> listOrdersId = new ArrayList<>();
    for (Order order : clientById.getOrdersHistory()) {
      listOrdersId.add(order.getId());
    }
    clientOrdersIdDto.setOrderIds(listOrdersId);

    return clientOrdersIdDto;
  }

  public List<ClientDto> findAll() {
    List<ClientDto> clientDtoArray = new ArrayList<>();
    var allClients = clientRepository.findAll();
    for (Client client : allClients) {
      clientDtoArray.add(objectMapper.convertValue(client, ClientDto.class));
    }
    return clientDtoArray;
  }

  public void allClientsInfo() {
    var clientDtoList = findAll();
    clientDtoList.forEach(clientDto -> log.info("{} : {}, {}, {}, {}", "Client", clientDto.getId(),
        clientDto.getName(), clientDto.getEmail(), clientDto.getPhone()));
  }

  public void shortClientOrdersInfo(int clientId) {
    var clientById = getClientInfoWithOrderIdsById(clientId);
    var message = "No Orders from this Client";
    if (clientById.getOrderIds() != null) {
      message = clientById.getOrderIds().toString();
    }
    log.info("{} : {}", "Client ID", clientById.getId());
    log.info("{} : {}", "Client Name", clientById.getName());
    log.info("{} : {}", "Client Email", clientById.getEmail());
    log.info("{} : {}", "Client Phone", clientById.getPhone());
    log.info("{} : {}", "OrderIDs", message);
  }

  public void shortClientInfo(int clientId) {
    var clientById = getShortInfoById(clientId);
    log.info("{} : {}", "Client ID", clientById.getId());
    log.info("{} : {}", "Client Name", clientById.getName());
    log.info("{} : {}", "Client Email", clientById.getEmail());
    log.info("{} : {}", "Client Phone", clientById.getPhone());
    log.info("{} : {}, {}, {}, {}", "Client Address", clientById.getAddress().getCountry(),
        clientById.getAddress().getCity(), clientById.getAddress().getStreet(),
        clientById.getAddress().getHouse());
  }

  public void fullClientInfo(int clientID) {
    shortClientInfo(clientID);
    var fullInfoById = getFullInfoById(clientID);
    log.info("{} : ", "Orders");
    var ordersHistory = fullInfoById.getOrdersHistory();
    if (ordersHistory == null) {
      log.info("No Orders from this Client");
      return;
    }
    ordersHistory.forEach(order -> {
          log.info("\t\t - {} : {}", "Order ID", order.getId());
          log.info("\t\t - {} : {}", "Sum of Order", order.getSumOfOrder());
          log.info("\t\t - {} :", "Products");
          var orderItemsDto = order.getOrderItems();
          orderItemsDto.forEach(orderItem -> {
            var productDto = orderItem.getProduct();
            log.info("\t\t {},{},{}", productDto.getName(), productDto.getDescription(),
                productDto.getPrice());
          });
        }
    );
  }
}
