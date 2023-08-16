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

import com.denuvo.devjmestrada.dtos.ProjectDTO;
import com.denuvo.devjmestrada.exceptions.ProjectNotFoundException;
import com.denuvo.devjmestrada.repository.entities.ProjectEntity;
import com.denuvo.devjmestrada.service.interfaces.IProjectService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ProjectController {

	@Autowired
	private IProjectService projectService;

	@Autowired
	private ModelMapper modelMapper;

	@Operation(summary = "Get a list of projects")
	@GetMapping("/project")
	public ResponseEntity<List<ProjectDTO>> getProjects() {
		List<ProjectEntity> projects = projectService.getProjects();
		return projects.isEmpty() ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(projects.stream().map(this::convertToDto).collect(Collectors.toList()));
	}

	@Operation(summary = "Get a project by its id")
	@GetMapping("/project/{id}")
	public ResponseEntity<ProjectDTO> getProjectById(@PathVariable("id") Long id) throws ProjectNotFoundException {
		ProjectEntity project = projectService.getProjectById(id);
		return ResponseEntity.ok(convertToDto(project));
	}

	@Operation(summary = "Post a project")
	@PostMapping("/project")
	@ResponseStatus(HttpStatus.CREATED) // Other way to handle responses
	public ProjectDTO addProject(@RequestBody ProjectDTO project) {
		ProjectDTO addedProject = convertToDto(projectService.addProject(convertToEntity(project)));
		return addedProject;
	}

	@Operation(summary = "Put a project with id integrity")
	@PutMapping("/project/{id}")
	public ResponseEntity<ProjectDTO> updateProject(@PathVariable("id") Long id, @RequestBody ProjectDTO newProject)
			throws ProjectNotFoundException {
		return !newProject.getId().equals(id) ? ResponseEntity.status(HttpStatus.CONFLICT).build()
				: ResponseEntity.ok(convertToDto(projectService.updateProject(id, convertToEntity(newProject))));
	}

	@Operation(summary = "Delete a project by id")
	@DeleteMapping("/project/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable("id") Long id) throws ProjectNotFoundException {
		projectService.deleteProject(id);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(ProjectNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<String> handleException(ProjectNotFoundException exception) {
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
	 * Converts a ProjectEntity object to a ProjectDTO object using ModelMapper.
	 *
	 * @param projectEntity The ProjectEntity object to be converted.
	 * @return A ProjectDTO object converted from the given ProjectEntity object.
	 * @throws ResponseStatusException If an error occurs during the mapping
	 *                                 process.
	 */
	private ProjectDTO convertToDto(ProjectEntity projectEntity) {
		ProjectDTO project = null;
		try {
			project = modelMapper.map(projectEntity, ProjectDTO.class);
		} catch (MappingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during mapping");
		}
		return project;
	}

	/**
	 * Converts a ProjectDTO object to a ProjectEntity object using ModelMapper.
	 *
	 * @param projectDTO The ProjectDTO object to be converted.
	 * @return A ProjectEntity object converted from the given ProjectDTO object.
	 * @throws ResponseStatusException If an error occurs during the mapping
	 *                                 process.
	 */
	private ProjectEntity convertToEntity(ProjectDTO projectDTO) {
		ProjectEntity project = null;
		try {
			project = modelMapper.map(projectDTO, ProjectEntity.class);
		} catch (MappingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during mapping");
		}
		return project;
	}
}
