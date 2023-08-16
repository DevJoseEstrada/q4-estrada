package com.denuvo.devjmestrada.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.denuvo.devjmestrada.dtos.CustomerDTO;
import com.denuvo.devjmestrada.exceptions.CustomerNotFoundException;
import com.denuvo.devjmestrada.repository.entities.CustomerEntity;
import com.denuvo.devjmestrada.service.interfaces.ICustomerService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private ModelMapper modelMapper;

	@Operation(summary = "Get a list of customers")
	@GetMapping("/customer")
	public ResponseEntity<List<CustomerDTO>> getCustomers() {
		List<CustomerEntity> customers = customerService.getCustomers();
		return customers.isEmpty() ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(customers.stream().map(this::convertToDto).collect(Collectors.toList()));
	}

	@Operation(summary = "Get a customer by its id")
	@GetMapping("/customer/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id) throws CustomerNotFoundException {
		CustomerEntity customer = customerService.getCustomerById(id);
		return ResponseEntity.ok(convertToDto(customer));
	}

	@Operation(summary = "Post a customer")
	@PostMapping("/customer")
	@ResponseStatus(HttpStatus.CREATED) // Other way to handle responses
	public CustomerDTO addCustomer(@RequestBody CustomerDTO customer) {
		CustomerDTO addedCustomer = convertToDto(customerService.addCustomer(convertToEntity(customer)));
		return addedCustomer;
	}

	@Operation(summary = "Put a customer with id integrity")
	@PutMapping("/customer/{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDTO newCustomer)
			throws CustomerNotFoundException {
		return !newCustomer.getId().equals(id) ? ResponseEntity.status(HttpStatus.CONFLICT).build()
				: ResponseEntity.ok(convertToDto(customerService.updateCustomer(id, convertToEntity(newCustomer))));
	}

	@Operation(summary = "Delete a customer by id")
	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) throws CustomerNotFoundException {
		customerService.deleteCustomer(id);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleException(CustomerNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
		return new ResponseEntity<>("Error illegal arguments detectect, check the request", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleGenericException(Exception exception) {
		return new ResponseEntity<>("An internal error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Converts a CustomerEntity object to a CustomerDTO object using ModelMapper.
	 *
	 * @param customerEntity The CustomerEntity object to be converted.
	 * @return A CustomerDTO object converted from the given CustomerEntity object.
	 * @throws ResponseStatusException If an error occurs during the mapping
	 *                                 process.
	 */
	private CustomerDTO convertToDto(CustomerEntity customerEntity) {
		CustomerDTO customer = null;
		try {
			customer = modelMapper.map(customerEntity, CustomerDTO.class);
		} catch (MappingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during mapping");
		}
		return customer;
	}

	/**
	 * Converts a CustomerDTO object to a CustomerEntity object using ModelMapper.
	 *
	 * @param customerDto The CustomerDTO object to be converted.
	 * @return A CustomerEntity object converted from the given CustomerDTO object.
	 * @throws ResponseStatusException If an error occurs during the mapping
	 *                                 process.
	 */
	private CustomerEntity convertToEntity(CustomerDTO customerDto) {
		CustomerEntity customer = null;
		try {
			customer = modelMapper.map(customerDto, CustomerEntity.class);
		} catch (MappingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during mapping");
		}
		return customer;
	}

}
