package com.denuvo.devjmestrada.exceptions;

/**
 * Exception class for indicating that a customer was not found.
 */
public class CustomerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new CustomerNotFoundException with the provided error message.
	 *
	 * @param message The error message indicating the reason for the exception.
	 */
	public CustomerNotFoundException(String message) {
		super(message);
	}

	/**
	 * Constructs a new CustomerNotFoundException with a message indicating that a
	 * customer with the given ID was not found.
	 *
	 * @param customerId The ID of the customer that was not found.
	 */
	public CustomerNotFoundException(long customerId) {
		super("Customer " + customerId + " not found");
	}

	/**
	 * Constructs a new CustomerNotFoundException with a generic message indicating
	 * that a customer was not found.
	 */
	public CustomerNotFoundException() {
		super("Customer not found");
	}
}
