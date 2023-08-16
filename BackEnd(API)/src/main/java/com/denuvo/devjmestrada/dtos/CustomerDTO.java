package com.denuvo.devjmestrada.dtos;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) representing customer information.
 */
public class CustomerDTO {
	private Long id;
	private String name;
	private String contact;
	private LocalDate creationDate;

	/**
	 * Default constructor.
	 */
	public CustomerDTO() {
		super();
	}

	/**
	 * Parameterized constructor to initialize the CustomerDTO object.
	 *
	 * @param id           The ID of the customer.
	 * @param name         The name of the customer.
	 * @param contact      The contact information of the customer.
	 * @param creationDate The creation date of the customer.
	 */
	public CustomerDTO(Long id, String name, String contact, LocalDate creationDate) {
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.creationDate = creationDate;
	}

	/**
	 * Gets the ID of the customer.
	 *
	 * @return The ID of the customer.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the ID of the customer.
	 *
	 * @param id The ID to set for the customer.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the name of the customer.
	 *
	 * @return The name of the customer.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the customer.
	 *
	 * @param name The name to set for the customer.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the contact information of the customer.
	 *
	 * @return The contact information of the customer.
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * Sets the contact information of the customer.
	 *
	 * @param contact The contact information to set for the customer.
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * Gets the creation date of the customer.
	 *
	 * @return The creation date of the customer.
	 */
	public LocalDate getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creation date of the customer.
	 *
	 * @param creationDate The creation date to set for the customer.
	 */
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

}
