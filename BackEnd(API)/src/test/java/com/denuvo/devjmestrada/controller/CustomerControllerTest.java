package com.denuvo.devjmestrada.controller;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

import com.denuvo.devjmestrada.exceptions.CustomerNotFoundException;
import com.denuvo.devjmestrada.repository.entities.CustomerEntity;
import com.denuvo.devjmestrada.service.interfaces.ICustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerController.class)
@ComponentScan(basePackages = "com.denuvo.devjmestrada.config")
public class CustomerControllerTest {
		
	private static final String END_POINT_PATH = "/api/customer";
    @Autowired
    private MockMvc mockMvc;
    @Autowired 
    private ObjectMapper objectMapper;
    @MockBean
    private ICustomerService customerService;
    CustomerEntity customerEntityOne;
    CustomerEntity customerEntityTwo;
    CustomerEntity customerEntityError;
    List<CustomerEntity> customerEntityList= new ArrayList<>();

    @BeforeEach
    void setUp() {
    	customerEntityOne = new CustomerEntity(1L, "Mark", "marck@mail.com", LocalDate.of(2023, 8, 11));
    	customerEntityTwo = new CustomerEntity(2L, "Will", "will@mail.com", LocalDate.of(2023, 8, 12));
    	customerEntityList = List.of(customerEntityOne, customerEntityTwo);
    	//Simulate name restriction to make error
    	String repeatedA = new String(new char[500]).replace('\0', 'a');
    	customerEntityError = new CustomerEntity(1L, repeatedA, null, LocalDate.of(-2023, 8, 12));
    	
    }
    
    @AfterEach
    void tearDown() {
    }
    
    @Test
    public void testAddShouldReturn400BadRequest() throws Exception {
        String requestBody = objectMapper.writeValueAsString(customerEntityError);
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
        String requestBody = objectMapper.writeValueAsString(customerEntityOne);
        when(customerService.addCustomer(customerEntityOne)).thenReturn(customerEntityOne);
        this.mockMvc.perform(post(END_POINT_PATH).contentType("application/json")
                .content(requestBody))
                .andExpect(status().isCreated())
                .andDo(print());
     
    }
    @Test
    public void testGetShouldReturn404NotFound() throws Exception {
        Long customerId = -1L;
        String requestURI = END_POINT_PATH + "/" + customerId;
        when(customerService.getCustomerById(customerId)).thenThrow(CustomerNotFoundException.class);
        this.mockMvc.perform(get(requestURI))
            .andExpect(status().isNotFound())
            .andDo(print());
    }
    @Test
    public void testListShouldReturn204NoContent() throws Exception {
        when(customerService.getCustomers()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get(END_POINT_PATH))
            .andExpect(status().isNoContent())
            .andDo(print());
    }
    @Test
    public void testListShouldReturn200OK() throws Exception {          
        when(customerService.getCustomers()).thenReturn(customerEntityList);
        this.mockMvc.perform(get(END_POINT_PATH))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$[1].name", is(customerEntityList.get(1).getName())))
            .andDo(print());
    }
    @Test
    public void testGetShouldReturn200OK() throws Exception {
        Long customerId = 1L;
        String requestURI = END_POINT_PATH + "/" + customerId;
        Mockito.when(customerService.getCustomerById(customerId)).thenReturn(customerEntityOne);
        this.mockMvc.perform(get(requestURI))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.name", is(customerEntityOne.getName())))
            .andExpect(jsonPath("$.contact", is(customerEntityOne.getContact())))
            .andDo(print());
    }
    @Test
    public void testUpdateShouldReturn404NotFound() throws Exception {
        Long customerId = 1L;
        String requestURI = END_POINT_PATH + "/" + customerId;
        Mockito.when(customerService.updateCustomer(customerId,customerEntityOne)).thenThrow(CustomerNotFoundException.class);
        String requestBody = objectMapper.writeValueAsString(customerEntityOne);
        this.mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
            .andExpect(status().isNotFound())
            .andDo(print());       
    }
    @Test
    public void testUpdateShouldReturn409ConflictRequest() throws Exception {
        Long customerId = 4L;
        String requestURI = END_POINT_PATH + "/" + customerId;
        String requestBody = objectMapper.writeValueAsString(customerEntityOne);   
        this.mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
            .andExpect(status().isConflict())
            .andDo(print());
    }
    @Test
    public void testUpdateShouldReturn400BadRequest() throws Exception {
        Long customerId = 1L;
          String requestURI = END_POINT_PATH + "/" + customerId;
        String requestBody = objectMapper.writeValueAsString(customerEntityError);   
        this.mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
            .andExpect(status().isBadRequest())
            .andDo(print());
    }
    @Test
    public void testUpdateShouldReturn500InternalServerError() throws Exception {
        Long customerId = -1L;
        String requestURI = END_POINT_PATH + "/" + customerId;
        String requestBody = objectMapper.writeValueAsString("");   
        this.mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
            .andExpect(status().isInternalServerError())
            .andDo(print());
    }
    @Test
    public void testUpdateShouldReturn200OK() throws Exception {
        Long customerId = 1L;
        String requestURI = END_POINT_PATH + "/" + customerId;
        Mockito.when(customerService.updateCustomer(customerId,customerEntityOne)).thenReturn(customerEntityOne);
        String requestBody = objectMapper.writeValueAsString(customerEntityOne);
        this.mockMvc.perform(put(requestURI).contentType("application/json").content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(customerEntityOne.getName())))
            .andExpect(jsonPath("$.contact", is(customerEntityOne.getContact())))
            .andDo(print());
    }
    @Test
    public void testDeleteShouldReturn404NotFound() throws Exception {
        Long customerId = 1L;
        String requestURI = END_POINT_PATH + "/" + customerId;    
        Mockito.doThrow(CustomerNotFoundException.class).when(customerService).deleteCustomer(customerId);   
        this.mockMvc.perform(delete(requestURI))
            .andExpect(status().isNotFound())
            .andDo(print());
    }
    @Test
    public void testDeleteShouldReturn200OK() throws Exception {
        Long customerId = 1L;
        String requestURI = END_POINT_PATH + "/" + customerId;
        Mockito.doNothing().when(customerService).deleteCustomer(customerId);
        this.mockMvc.perform(delete(requestURI))
            .andExpect(status().isNoContent())
            .andDo(print());
    }
}
