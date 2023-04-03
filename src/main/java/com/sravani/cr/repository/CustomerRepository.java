package com.sravani.cr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sravani.cr.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
