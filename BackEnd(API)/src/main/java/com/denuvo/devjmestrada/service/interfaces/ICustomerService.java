package com.denuvo.devjmestrada.service.interfaces;

import java.util.List;

import com.denuvo.devjmestrada.exceptions.CustomerNotFoundException;
import com.denuvo.devjmestrada.repository.entities.CustomerEntity;

/**
 * Interface for defining customer-related service operations.
 */
public interface ICustomerService {

	/**
	 * Retrieves a customer by their ID.
	 *
	 * @param id The ID of the customer to retrieve.
	 * @return The retrieved CustomerEntity.
	 * @throws CustomerNotFoundException If the customer with the given ID is not
	 *                                   found.
	 */
	CustomerEntity getCustomerById(Long id) throws CustomerNotFoundException;

	/**
	 * Retrieves a list of all customers.
	 *
	 * @return A list of CustomerEntity objects representing all customers.
	 */
	List<CustomerEntity> getCustomers();

	/**
	 * Adds a new customer.
	 *
	 * @param newCustomer The new CustomerEntity to be added.
	 * @return The added CustomerEntity.
	 */
	CustomerEntity addCustomer(CustomerEntity newCustomer);

	/**
	 * Updates a customer with the provided information.
	 *
	 * @param id       The ID of the customer to be updated.
	 * @param customer The updated CustomerEntity information.
	 * @return The updated CustomerEntity.
	 * @throws CustomerNotFoundException If the customer with the given ID is not
	 *                                   found.
	 */
	CustomerEntity updateCustomer(Long id, CustomerEntity customer) throws CustomerNotFoundException;

	/**
	 * Deletes a customer by their ID.
	 *
	 * @param id The ID of the customer to be deleted.
	 * @throws CustomerNotFoundException If the customer with the given ID is not
	 *                                   found.
	 */
	void deleteCustomer(Long id) throws CustomerNotFoundException;
}
