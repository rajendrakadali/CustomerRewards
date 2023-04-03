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
import com.sravani.cr.model.Transaction;
import com.sravani.cr.service.OrderService;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@AutoConfigureMockMvc
public class OrderControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private OrderController orderController;
	
	@Mock
	OrderService orderService;

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}
	
	
	@Test
    @DisplayName("POST /order/recieveOrder")
    void testRegisterCustomer() throws Exception {
        
        Transaction orderToPost = new Transaction(null,20.21f,null,null,1);
        Transaction orderToReturn = new Transaction(1,20.21f,0,null,1);
        
        doReturn(orderToReturn).when(orderService).save(any());

        
        mockMvc.perform(post("/order/recieveOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(orderToPost)))
                .andExpect(status().isOk())

                // Validate the returned fields
                .andExpect(jsonPath("$.transId", is(1)))
                .andExpect(jsonPath("$.tranAmt", is(20.21)))
                .andExpect(jsonPath("$.rewardPoints", is(0)))
                .andExpect(jsonPath("$.custId", is(1)));
    }

	@Test
	@DisplayName("GET /order/")
    public void getOrders() throws Exception {
        mockMvc.perform(get("/order/")).andExpect(status().isOk());
    }
	
	
	@Test
	@DisplayName("GET /order/{transId}")
    public void getOrder() throws Exception {
        mockMvc.perform(get("/order/1")).andExpect(status().isOk());
    }
	
	@Test
	@DisplayName("GET /order/customer/{custId}")
    public void getOrdersByCustomerId() throws Exception {
        mockMvc.perform(get("/order/customer/1")).andExpect(status().isOk());
    }
	
	
	static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
