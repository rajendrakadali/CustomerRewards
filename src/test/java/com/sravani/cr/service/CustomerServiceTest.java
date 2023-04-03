package com.sravani.cr.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sravani.cr.model.Customer;
import com.sravani.cr.repository.CustomerRepository;
@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {
	
	@InjectMocks
	private CustomerService customerService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	Customer customerToSave;
	Customer customerToReturn;
	
	@BeforeEach
	void setup() {
		customerToSave = new Customer(null,"Vinod", "Kumar" , "Shasha",null);
        customerToReturn = new Customer(1,"Vinod", "Kumar" , "Shasha",null);
	}
	
	
	@Test
	public void save() {
		when(customerRepository.save(any())).thenReturn(customerToReturn);
		customerService.save(customerToSave);
	}
	@Test
	public void getCustomers() {
		List<Customer>  customers = Arrays.asList(customerToReturn);
		when(customerRepository.findAll()).thenReturn(customers);
		customerService.getCustomers();
	}
	@Test
	public void getCustomer() {
		Optional<Customer> customer = Optional.of(customerToReturn);
		when(customerRepository.findById(anyInt())).thenReturn(customer);
		customerService.getCustomer(1);
	}
}
