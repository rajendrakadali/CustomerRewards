package com.sravani.cr.controller;

import com.sravani.cr.model.Transaction;
import com.sravani.cr.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
