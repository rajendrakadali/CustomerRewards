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

import com.sravani.cr.model.Transaction;
import com.sravani.cr.service.OrderService;


@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	
	
	@PostMapping("/recieveOrder")
	public Transaction recieveOrder(@Valid @RequestBody Transaction transaction) {
		return  orderService.save(transaction);
	}

	@GetMapping("/")
	public List<Transaction> getOrders() {
		return orderService.getOrders();
	}
	
	@GetMapping("/{transId}")
	public Transaction getOrderByTransId(@PathVariable(value = "transId") Integer transId) {
		return orderService.getOrderByTransId(transId);
	}
	
	@GetMapping("/customer/{custId}")
	public List<Transaction> getOrdersByCustomerId(@PathVariable(value = "custId") Integer custId) {
		return orderService.getOrdersByCustId(custId);
	}
	
}
