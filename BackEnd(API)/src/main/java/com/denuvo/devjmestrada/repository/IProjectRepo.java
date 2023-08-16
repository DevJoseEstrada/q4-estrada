package com.denuvo.devjmestrada.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.denuvo.devjmestrada.repository.entities.ProjectEntity;
@RepositoryRestResource(exported = false)
public interface IProjectRepo extends JpaRepository<ProjectEntity,Long>{

}
