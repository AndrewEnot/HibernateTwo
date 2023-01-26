package com.example.hibernatetwo.service;

import com.example.hibernatetwo.dto.ProductDto;
import com.example.hibernatetwo.model.Product;
import com.example.hibernatetwo.repository.ProductRepository;
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
 * 15:49
 */
@Service
@Data
@Slf4j
public class ProductService {

  private final ProductRepository productRepository;
  private final ObjectMapper objectMapper;

  public ProductDto createProduct(ProductDto productDto) {
    if (productDto == null) {
      log.info("INPUTTED DATA with null!!! ");
      return new ProductDto();
    }
    var product = objectMapper.convertValue(productDto, Product.class);
    productRepository.save(product);
    productDto.setId(product.getId());
    return productDto;
  }

  public ProductDto findById(int id) {
    var product = productRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("No product with such ID!"));
    return objectMapper.convertValue(product, ProductDto.class);
  }

  public List<ProductDto> findAll() {
    List<ProductDto> productDtoList = new ArrayList<>();
    var products = productRepository.findAll();
    for (Product product : products) {
      productDtoList.add(objectMapper.convertValue(product, ProductDto.class));
    }
    return productDtoList;
  }

  public void allProductsInfo() {
    var allProducts = findAll();
    allProducts.forEach(productDto -> log.info("{} : {}, {}, {}, {}", "Product", productDto.getId(),
        productDto.getName(), productDto.getDescription(), productDto.getPrice()));
  }
}
