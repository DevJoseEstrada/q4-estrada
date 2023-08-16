package com.denuvo.devjmestrada.repository.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Entity class representing customer information in a database.
 */
@Entity
@Table(name = "Customer")
public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(name = "name", length = 250, nullable = true, unique = false)
	String name;

	@Column(name = "contact", nullable = true, unique = false)
	String contact;

	@Column(name = "creationDate", nullable = true, unique = false)
	LocalDate creationDate;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	List<ProjectEntity> projects;

	/**
	 * Parameterized constructor to initialize the CustomerEntity object with
	 * projects.
	 *
	 * @param id           The ID of the customer.
	 * @param name         The name of the customer.
	 * @param contact      The contact information of the customer.
	 * @param creationDate The creation date of the customer.
	 * @param projects     The list of projects associated with the customer.
	 */
	public CustomerEntity(Long id, String name, String contact, LocalDate creationDate, List<ProjectEntity> projects) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.creationDate = creationDate;
		this.projects = projects;
	}

	/**
	 * Parameterized constructor to initialize the CustomerEntity object without
	 * projects.
	 *
	 * @param id           The ID of the customer.
	 * @param name         The name of the customer.
	 * @param contact      The contact information of the customer.
	 * @param creationDate The creation date of the customer.
	 */
	public CustomerEntity(Long id, String name, String contact, LocalDate creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.creationDate = creationDate;
	}

	/**
	 * Default constructor.
	 */
	public CustomerEntity() {
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
	 * @return the contact
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
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
	 * @return the projects
	 */
	public List<ProjectEntity> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(List<ProjectEntity> projects) {
		this.projects = projects;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contact, creationDate, id, name, projects);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerEntity other = (CustomerEntity) obj;
		return Objects.equals(contact, other.contact) && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(projects, other.projects);
	}
}
