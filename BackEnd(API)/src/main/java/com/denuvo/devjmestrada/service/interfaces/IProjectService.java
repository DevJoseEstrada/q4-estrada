package com.denuvo.devjmestrada.service.interfaces;

import java.util.List;

import com.denuvo.devjmestrada.exceptions.ProjectNotFoundException;
import com.denuvo.devjmestrada.repository.entities.ProjectEntity;

/**
 * Interface for defining project-related service operations.
 */
public interface IProjectService {

	/**
	 * Retrieves a project by its ID.
	 *
	 * @param id The ID of the project to retrieve.
	 * @return The retrieved ProjectEntity.
	 * @throws ProjectNotFoundException If the project with the given ID is not
	 *                                  found.
	 */
	ProjectEntity getProjectById(Long id) throws ProjectNotFoundException;

	/**
	 * Retrieves a list of all projects.
	 *
	 * @return A list of ProjectEntity objects representing all projects.
	 */
	List<ProjectEntity> getProjects();

	/**
	 * Adds a new project.
	 *
	 * @param newProject The new ProjectEntity to be added.
	 * @return The added ProjectEntity.
	 */
	ProjectEntity addProject(ProjectEntity newProject);

	/**
	 * Updates a project with the provided information.
	 *
	 * @param id      The ID of the project to be updated.
	 * @param project The updated ProjectEntity information.
	 * @return The updated ProjectEntity.
	 * @throws ProjectNotFoundException If the project with the given ID is not
	 *                                  found.
	 */
	ProjectEntity updateProject(Long id, ProjectEntity project) throws ProjectNotFoundException;

	/**
	 * Deletes a project by its ID.
	 *
	 * @param id The ID of the project to be deleted.
	 * @throws ProjectNotFoundException If the project with the given ID is not
	 *                                  found.
	 */
	void deleteProject(Long id) throws ProjectNotFoundException;
}
