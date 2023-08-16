package com.denuvo.devjmestrada.controller;

import com.denuvo.devjmestrada.exceptions.ProjectNotFoundException;
import com.denuvo.devjmestrada.repository.entities.CustomerEntity;
import com.denuvo.devjmestrada.repository.entities.ProjectEntity;
import com.denuvo.devjmestrada.service.interfaces.IProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
@ComponentScan(basePackages = "com.denuvo.devjmestrada.config")
public class ProjectControllerTest {
	
	private static final String END_POINT_PATH = "/api/project";

    @Autowired
    private MockMvc mockMvc;
    @Autowired 
    private ObjectMapper objectMapper;
    @MockBean
    private IProjectService projectService;
    
    ProjectEntity projectEntityOne;
    ProjectEntity projectEntityTwo;
    ProjectEntity projectEntityError;
    List<ProjectEntity> projectEntityList= new ArrayList<>();

    @BeforeEach
    void setUp() {
    	projectEntityOne = new ProjectEntity(1L, "Denuvo projet", "Project made by denuvo", LocalDate.of(2015, 8, 12),
    			new CustomerEntity(1L, "Mark", "mark@mail.com", LocalDate.of(2023, 8, 11)));
    	projectEntityTwo =new ProjectEntity(2L, "valve projet", "Project made by valve", LocalDate.of(2018, 8, 12),
    			new CustomerEntity(1L, "Will", "Will@mail.com", LocalDate.of(2023, 8, 12)));
    	projectEntityList = List.of(projectEntityOne, projectEntityTwo);
    	//Simulate name restriction to make error
    	String repeatedA = new String(new char[500]).replace('\0', 'a');
    	projectEntityError =new ProjectEntity(1L, repeatedA, "", LocalDate.of(-1234, 8, 12),
    			new CustomerEntity(-1L, repeatedA, null, LocalDate.of(-2023, 8, 12)));
    	
    }
    
    @AfterEach
    void tearDown() {
    }
    
    @Test
    public void testAddShouldReturn400BadRequest() throws Exception {
        String requestBody = objectMapper.writeValueAsString(projectEntityError);
        this.mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    @Test
    public void testAddShouldReturn500InternalServerError() throws Exception {
        String requestBody = objectMapper.writeValueAsString("");
        this.mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                .content(requestBody))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }
    @Test
    public void testAddShouldReturn201Created() throws Exception {
        String requestBody = objectMapper.writeValueAsString(projectEntityOne);
        when(projectService.addProject(projectEntityOne)).thenReturn(projectEntityOne);
        this.mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                .content(requestBody))
                .andExpect(status().isCreated())
                .andDo(print());
     
    }
    @Test
    public void testGetShouldReturn404NotFound() throws Exception {
        Long projectId = -1L;
        String requestURI = END_POINT_PATH + "/" + projectId;
        when(projectService.getProjectById(projectId)).thenThrow(ProjectNotFoundException.class);
        this.mockMvc.perform(get(requestURI))
            .andExpect(status().isNotFound())
            .andDo(print());
    }
    @Test
    public void testListShouldReturn204NoContent() throws Exception {
        when(projectService.getProjects()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get(END_POINT_PATH))
            .andExpect(status().isNoContent())
            .andDo(print());
    }
    @Test
    public void testListShouldReturn200OK() throws Exception {          
        when(projectService.getProjects()).thenReturn(projectEntityList);
        this.mockMvc.perform(get(END_POINT_PATH))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$[1].name", is(projectEntityList.get(1).getName())))
            .andDo(print());
    }
    @Test
    public void testGetShouldReturn200OK() throws Exception {
        Long projectId = 1L;
        String requestURI = END_POINT_PATH + "/" + projectId;
        Mockito.when(projectService.getProjectById(projectId)).thenReturn(projectEntityOne);
        this.mockMvc.perform(get(requestURI))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.name", is(projectEntityOne.getName())))
            .andExpect(jsonPath("$.descriptoin", is(projectEntityOne.getDescriptoin())))
            .andDo(print());
    }
    @Test
    public void testUpdateShouldReturn404NotFound() throws Exception {
        Long projectId = 1L;
        String requestURI = END_POINT_PATH + "/" + projectId;
        Mockito.when(projectService.updateProject(projectId,projectEntityOne)).thenThrow(ProjectNotFoundException.class);
        String requestBody = objectMapper.writeValueAsString(projectEntityOne);
        this.mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
            .andExpect(status().isNotFound())
            .andDo(print());       
    }
    @Test
    public void testUpdateShouldReturn409ConflictRequest() throws Exception {
        Long projectId = 4L;
        String requestURI = END_POINT_PATH + "/" + projectId;
        String requestBody = objectMapper.writeValueAsString(projectEntityOne);   
        this.mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
            .andExpect(status().isConflict())
            .andDo(print());
    }
    @Test
    public void testUpdateShouldReturn400BadRequest() throws Exception {
        Long projectId = 1L;
          String requestURI = END_POINT_PATH + "/" + projectId;
        String requestBody = objectMapper.writeValueAsString(projectEntityError);   
        this.mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
            .andExpect(status().isBadRequest())
            .andDo(print());
    }
    @Test
    public void testUpdateShouldReturn500InternalServerError() throws Exception {
        Long projectId = -1L;
        String requestURI = END_POINT_PATH + "/" + projectId;
        String requestBody = objectMapper.writeValueAsString("");   
        this.mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
            .andExpect(status().isInternalServerError())
            .andDo(print());
    }
    @Test
    public void testUpdateShouldReturn200OK() throws Exception {
        Long projectId = 1L;
        String requestURI = END_POINT_PATH + "/" + projectId;
        Mockito.when(projectService.updateProject(projectId,projectEntityOne)).thenReturn(projectEntityOne);
        String requestBody = objectMapper.writeValueAsString(projectEntityOne);
        this.mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(projectEntityOne.getName())))
            .andExpect(jsonPath("$.descriptoin", is(projectEntityOne.getDescriptoin())))
            .andDo(print());
    }
    @Test
    public void testDeleteShouldReturn404NotFound() throws Exception {
        Long projectId = 1L;
        String requestURI = END_POINT_PATH + "/" + projectId;    
        Mockito.doThrow(ProjectNotFoundException.class).when(projectService).deleteProject(projectId);   
        this.mockMvc.perform(delete(requestURI))
            .andExpect(status().isNotFound())
            .andDo(print());
    }
    @Test
    public void testDeleteShouldReturn200OK() throws Exception {
        Long projectId = 1L;
        String requestURI = END_POINT_PATH + "/" + projectId;
        Mockito.doNothing().when(projectService).deleteProject(projectId);
        this.mockMvc.perform(delete(requestURI))
            .andExpect(status().isNoContent())
            .andDo(print());
    }
}
