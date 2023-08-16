package com.denuvo.devjmestrada.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.denuvo.devjmestrada.repository.entities.CustomerEntity;
@RepositoryRestResource(exported = false)
public interface ICustomerRepo extends JpaRepository<CustomerEntity,Long>{

}
