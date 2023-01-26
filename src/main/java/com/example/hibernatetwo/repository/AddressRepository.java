package com.example.hibernatetwo.repository;

import com.example.hibernatetwo.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 15:12
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
