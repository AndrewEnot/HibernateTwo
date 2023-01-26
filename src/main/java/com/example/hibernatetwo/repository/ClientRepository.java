package com.example.hibernatetwo.repository;

import com.example.hibernatetwo.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * @author Oksiuta Andrii
 * 24.01.2023
 * 15:14
 */
@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

}
