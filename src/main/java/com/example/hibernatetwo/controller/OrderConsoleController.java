package com.example.hibernatetwo.controller;

import com.example.hibernatetwo.dto.AddressDto;
import com.example.hibernatetwo.dto.ClientDto;
import com.example.hibernatetwo.dto.OrderDto;
import com.example.hibernatetwo.dto.ProductDto;
import com.example.hibernatetwo.service.AddressService;
import com.example.hibernatetwo.service.ClientService;
import com.example.hibernatetwo.service.OrderService;
import com.example.hibernatetwo.service.ProductService;
import java.util.Scanner;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*
 * @author Oksiuta Andrii
 * 17.01.2023
 * 17:16
 */
@Data
@Service
@Slf4j

public class OrderConsoleController {

  private final OrderService orderService;
  private final ClientService clientService;
  private final ProductService productService;
  private final AddressService addressService;

  /**
   * This method with main logic for this work. Switcher with step by step confirmation.
   */
  public void start() {
    log.info("App start working.");

    while (true) {
      var command = getScanner("""
          Enter command of action:
          0 - create new Order
          1 - add new Product
          2 - add new Client
          3 - change address of Client
          4 - get full info about Client and his Orders
          5 - get short info about Client (only with personal data)
          6 - get report about Client and list of his Order`s Ids
          7 - get full List of Clients
          8 - get full List of Products
          9 - finish work with App
          -->\s""");
      switch (Integer.parseInt(command)) {
        case 0 -> createNewOrder();
        case 1 -> createNewProduct();
        case 2 -> addNewClient();
        case 3 -> changeClientAddress();
        case 4 -> {
          var clientId = Integer.parseInt(getScanner(
              "Enter ID of Client, you'd like to get full info => "));
          clientService.fullClientInfo(clientId);;
        }
        case 5 -> {
          var clientId = Integer.parseInt(getScanner(
              "Enter ID of Client, you'd like to get short info => "));
          clientService.shortClientInfo(clientId);
        }
        case 6 -> {
          var clientId = Integer.parseInt(getScanner(
              "Enter ID of Client, you'd like to get partial info => "));
          clientService.shortClientOrdersInfo(clientId);
        }
        case 7 -> clientService.allClientsInfo();
        case 8 -> productService.allProductsInfo();
        case 9 -> {
          return;
        }
        default -> System.out.println("wrong command!!!");
      }
    }
  }

  /**
   * This method creates new instance of Order with adding Products to it, one by one.
   */
  private void createNewOrder() {
    var clientId = Integer.parseInt(getScanner(
        "Add ID of Client, you'd like to add to Order => "));
    var orderDto = new OrderDto();
    orderDto.setClient(clientService.findById(clientId));
    while (true) {
      var productId = Integer.parseInt(getScanner(
          "Add ID of Product, you'd like to add to Order => "));
      var quantity = Integer.parseInt(getScanner(
          "Add Quantity of Product => "));
      orderService.addOrderItem(orderDto, productId, quantity);
      var scanner = getScanner("Would you like to add another product (Y/N)? => ");
      if (!scanner.equalsIgnoreCase("Y")) {
        break;
      }
    }
  }

  private void createNewProduct() {
    var productName = getScanner(
        "Add Name of Product, you'd like to create => ");
    var productDescription = getScanner(
        "Add Short description of Product => ");
    var productPrice = Double.parseDouble(getScanner(
        "Add productPrice of Product => "));
    var productDto = new ProductDto(null, productName, productDescription, productPrice);
    productService.createProduct(productDto);
  }

  private void addNewClient() {
    var clientName = getScanner(
        "Enter Name of Client, you'd like to add => ");
    var clientEmail = getScanner(
        "Enter Email of Client => ");
    var clientPhone = getScanner(
        "Enter phone number of Client => ");
    var clientDto = new ClientDto(null, clientName, clientEmail, clientPhone);
    clientDto = clientService.createClient(clientDto);
    var addressDto = new AddressDto();
    addAddressConsole(clientDto, addressDto);
  }

  private void changeClientAddress() {
    var clientID = Integer.parseInt(getScanner(
        "Enter ID of Client, you'd like to change address => "));
    var clientDto = clientService.findById(clientID);//checking id for exist
    var addressByClientID = addressService.findByClientID(clientID);
    addAddressConsole(clientDto, addressByClientID);
  }

  private AddressDto addAddressConsole(ClientDto clientDto, AddressDto addressDto) {
    boolean isAddress = true;
    while (isAddress) {
      var clientAddress = getScanner(
          "Enter address of Client for example - (country,city,street,house) => ");
      var strings = clientAddress.toLowerCase().split(",");
      if (strings.length == 4) {
        addressDto.setClient(clientDto);
        addressDto.setCountry(strings[0]);
        addressDto.setCity(strings[1]);
        addressDto.setStreet(strings[2]);
        addressDto.setHouse(strings[3]);
        isAddress = false;
      } else {
        var scanner = getScanner("Incorrect address entry!!! try again? (Y/N) => ");
        if (!scanner.equalsIgnoreCase("Y")) {
          break;
        }
      }
    }
    return addressService.addAddress(addressDto);
  }

  public static String getScanner(String string) {
    var textIn = new Scanner(System.in);
    log.info(string);
    return textIn.next();
  }
}
