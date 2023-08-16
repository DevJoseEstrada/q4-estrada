package com.denuvo.devjmestrada.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denuvo.devjmestrada.exceptions.ProjectNotFoundException;
import com.denuvo.devjmestrada.repository.IProjectRepo;
import com.denuvo.devjmestrada.repository.entities.ProjectEntity;
import com.denuvo.devjmestrada.service.interfaces.IProjectService;

@Service
public class ProjectService implements IProjectService {

	@Autowired
	private IProjectRepo projectRepo;

	@Override
	public ProjectEntity getProjectById(Long id) throws ProjectNotFoundException {
		return projectRepo.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
	}

	@Override
	public List<ProjectEntity> getProjects() {
		return projectRepo.findAll();
	}

	@Override
	public ProjectEntity addProject(ProjectEntity projectEntity) {
		return projectRepo.save(projectEntity);
	}

	@Override
	public ProjectEntity updateProject(Long id, ProjectEntity projectEntity) throws ProjectNotFoundException {
		ProjectEntity project = projectRepo.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
		projectEntity.setId(project.getId());
		return projectRepo.save(projectEntity);
	}

	@Override
	public void deleteProject(Long id) throws ProjectNotFoundException {
		projectRepo.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
		projectRepo.deleteById(id);
	}
}
