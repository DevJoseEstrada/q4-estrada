package com.denuvo.devjmestrada.repository.entities;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entity class representing project information in a database.
 */
@Entity
@Table(name = "Project")
public class ProjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "name", length = 250, nullable = true, unique = false)
	String name;

	@Column(name = "descriptoin", nullable = true, unique = false)
	String descriptoin;

	@Column(name = "creationDate", nullable = true, unique = false)
	LocalDate creationDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	CustomerEntity customer;

	/**
	 * Parameterized constructor to initialize the ProjectEntity object.
	 *
	 * @param id           The ID of the project.
	 * @param name         The name of the project.
	 * @param descriptoin  The description of the project.
	 * @param creationDate The creation date of the project.
	 * @param customer     The associated CustomerEntity object for the project.
	 */
	public ProjectEntity(Long id, String name, String descriptoin, LocalDate creationDate, CustomerEntity customer) {
		super();
		this.id = id;
		this.name = name;
		this.descriptoin = descriptoin;
		this.creationDate = creationDate;
		this.customer = customer;
	}

	/**
	 * Default constructor.
	 */
	public ProjectEntity() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the descriptoin
	 */
	public String getDescriptoin() {
		return descriptoin;
	}

	/**
	 * @param descriptoin the descriptoin to set
	 */
	public void setDescriptoin(String descriptoin) {
		this.descriptoin = descriptoin;
	}

	/**
	 * @return the creationDate
	 */
	public LocalDate getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the customer
	 */
	public CustomerEntity getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creationDate, customer, descriptoin, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectEntity other = (ProjectEntity) obj;
		return Objects.equals(creationDate, other.creationDate) && Objects.equals(customer, other.customer)
				&& Objects.equals(descriptoin, other.descriptoin) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name);
	}

}
