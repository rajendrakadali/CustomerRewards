package com.sravani.cr.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sravani.cr.model.Customer;
import com.sravani.cr.service.CustomerService;



@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	
	@PostMapping("/register")
	public Customer registerCustomer(@Valid @RequestBody Customer customer) {
		return customerService.save(customer);
	}

	@GetMapping("/")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	
	@GetMapping("/{custId}")
	public Customer getCustomer(@PathVariable(value = "custId") Integer custId) {
		return customerService.getCustomer(custId);
	}

}
