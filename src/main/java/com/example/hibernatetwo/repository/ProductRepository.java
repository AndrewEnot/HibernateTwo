package com.example.hibernatetwo.repository;

import com.example.hibernatetwo.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 15:16
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
