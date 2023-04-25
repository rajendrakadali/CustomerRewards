package com.sravani.cr.controller;

import com.sravani.cr.exception.UserAlreadyExistsException;
import com.sravani.cr.model.Customer;
import com.sravani.cr.service.CustomerService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	
	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) throws UserAlreadyExistsException {
		try{
			Customer savedCustomer = customerService.save(customer);
			return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
		}catch (UserAlreadyExistsException ex) {
			throw new UserAlreadyExistsException(ex.getMessage());
		}
	}

	/**
	 * 	Only allows the user with ADMIN Authority user to view list of customers
	 */
	@GetMapping("/")
	@PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
	public List<Customer> getCustomers(Authentication auth) {
		return customerService.getCustomers();
	}

	/**
	 * 	Only allows the same user to view their details
	 */
	@GetMapping("/{custId}")
	@PostAuthorize("returnObject.user.username == authentication.name")
	public Customer getCustomer(@PathVariable(value = "custId") Integer custId) {
		return customerService.getCustomer(custId);
	}

}
