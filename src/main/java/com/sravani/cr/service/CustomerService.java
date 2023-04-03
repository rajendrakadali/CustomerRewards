package com.sravani.cr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sravani.cr.model.Customer;
import com.sravani.cr.repository.CustomerRepository;

@Service
public class CustomerService {
	
	
	@Autowired
	CustomerRepository customerRepository;
	
	
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
	
	public Customer getCustomer(Integer custId) {
		return customerRepository.findById(custId).get();
	}

}
