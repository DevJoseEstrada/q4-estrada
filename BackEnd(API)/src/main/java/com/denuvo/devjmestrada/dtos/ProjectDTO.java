package com.denuvo.devjmestrada.dtos;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) representing project information.
 */
public class ProjectDTO {

	Long id;
	String name;
	String descriptoin;
	LocalDate creationDate;
	CustomerDTO customer;

	/**
	 * Default constructor.
	 */
	public ProjectDTO() {
		super();
	}

	/**
	 * Parameterized constructor to initialize the ProjectDTO object.
	 *
	 * @param id           The ID of the project.
	 * @param name         The name of the project.
	 * @param descriptoin  The description of the project.
	 * @param creationDate The creation date of the project.
	 * @param customer     The associated CustomerDTO object for the project.
	 */
	public ProjectDTO(Long id, String name, String descriptoin, LocalDate creationDate, CustomerDTO customer) {
		super();
		this.id = id;
		this.name = name;
		this.descriptoin = descriptoin;
		this.creationDate = creationDate;
		this.customer = customer;
	}

	/**
	 * Gets the ID of the project.
	 *
	 * @return The ID of the project.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the ID of the project.
	 *
	 * @param id The ID to set for the project.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the name of the project.
	 *
	 * @return The name of the project.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the project.
	 *
	 * @param name The name to set for the project.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description of the project.
	 *
	 * @return The description of the project.
	 */
	public String getDescriptoin() {
		return descriptoin;
	}

	/**
	 * Sets the description of the project.
	 *
	 * @param descriptoin The description to set for the project.
	 */
	public void setDescriptoin(String descriptoin) {
		this.descriptoin = descriptoin;
	}

	/**
	 * Gets the creation date of the project.
	 *
	 * @return The creation date of the project.
	 */
	public LocalDate getCreationDate() {
		return creationDate;
	}

	/**
	 * Sets the creation date of the project.
	 *
	 * @param creationDate The creation date to set for the project.
	 */
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Gets the associated CustomerDTO object for the project.
	 *
	 * @return The associated CustomerDTO object for the project.
	 */
	public CustomerDTO getCustomer() {
		return customer;
	}

	/**
	 * Sets the associated CustomerDTO object for the project.
	 *
	 * @param customer The CustomerDTO object to associate with the project.
	 */
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
}
