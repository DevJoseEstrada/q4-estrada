package com.denuvo.devjmestrada.exceptions;

/**
 * Exception class for indicating that a project was not found.
 */
public class ProjectNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new ProjectNotFoundException with the provided error message.
	 *
	 * @param message The error message indicating the reason for the exception.
	 */
	public ProjectNotFoundException(String message) {
		super(message);
	}

	/**
	 * Constructs a new ProjectNotFoundException with a message indicating that a
	 * project with the given ID was not found.
	 *
	 * @param projectId The ID of the project that was not found.
	 */
	public ProjectNotFoundException(long projectId) {
		super("Project " + projectId + " not found");
	}

	/**
	 * Constructs a new ProjectNotFoundException with a generic message indicating
	 * that a project was not found.
	 */
	public ProjectNotFoundException() {
		super("Project not found");
	}
}
