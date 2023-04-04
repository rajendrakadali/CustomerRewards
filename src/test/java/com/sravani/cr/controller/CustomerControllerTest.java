package com.sravani.cr.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sravani.cr.model.Customer;
import com.sravani.cr.service.CustomerService;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@AutoConfigureMockMvc
public class CustomerControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private CustomerController customerController;
	
	@Mock
	private CustomerService customerService;


	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	
	
	static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}


	@Test
    @DisplayName("POST /customer/register")
    void testRegisterCustomer() throws Exception {
        
        Customer customerToPost = new Customer(null,"Vinod", "Kumar" , "Shasha",null);
        Customer customerToReturn = new Customer(1,"Vinod", "Kumar" , "Shasha",null);
        
        doReturn(customerToReturn).when(customerService).save(any());

        mockMvc.perform(post("/customer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerToPost)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.custId", is(1)))
                .andExpect(jsonPath("$.firstName", is("Vinod")))
                .andExpect(jsonPath("$.middleName", is("Kumar")))
                .andExpect(jsonPath("$.lastName", is("Shasha")));
    }
	
	@Test
    @DisplayName("POST /customer/register Error")
    void testRegisterCustomerError() throws Exception {
        
        Customer customerToPost = new Customer(null,null, "Kumar" , "Shasha",null);
        Customer customerToReturn = new Customer(1,null, "Kumar" , "Shasha",null);
        
        doReturn(customerToReturn).when(customerService).save(any());

        mockMvc.perform(post("/customer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerToPost)))
                .andExpect(status().is4xxClientError());
                
    }

	@Test
	@DisplayName("GET /customer/")
    public void getCustomers() throws Exception {
        mockMvc.perform(get("/customer/")).andExpect(status().isOk());
    }
	
	
	@Test
	@DisplayName("GET /customer/{custId}")
    public void getCustomer() throws Exception {
        mockMvc.perform(get("/customer/1")).andExpect(status().isOk());
    }
}
