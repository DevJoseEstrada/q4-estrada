package com.denuvo.devjmestrada.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denuvo.devjmestrada.exceptions.CustomerNotFoundException;
import com.denuvo.devjmestrada.repository.ICustomerRepo;
import com.denuvo.devjmestrada.repository.entities.CustomerEntity;
import com.denuvo.devjmestrada.service.interfaces.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepo customerRepo;

	@Override
	public CustomerEntity getCustomerById(Long id) throws CustomerNotFoundException {
		return customerRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
	}

	@Override
	public List<CustomerEntity> getCustomers() {
		return customerRepo.findAll();
	}

	@Override
	public CustomerEntity addCustomer(CustomerEntity customerEntity) {
		return customerRepo.save(customerEntity);
	}

	@Override
	public CustomerEntity updateCustomer(Long id, CustomerEntity customerEntity) throws CustomerNotFoundException {
		CustomerEntity customer = customerRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
		customerEntity.setId(customer.getId());
		customerEntity.setProjects(customer.getProjects());
		return customerRepo.save(customerEntity);
	}

	@Override
	public void deleteCustomer(Long id) throws CustomerNotFoundException {
		customerRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
		customerRepo.deleteById(id);
	}
}
