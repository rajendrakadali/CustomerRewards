package com.sravani.cr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sravani.cr.model.Transaction;

public interface OrderRepository extends JpaRepository<Transaction, Integer>{
	@Query("SELECT t FROM Transaction t WHERE t.custId = ?1")
	List<Transaction> findOrdersByCustId(Integer custId);
}