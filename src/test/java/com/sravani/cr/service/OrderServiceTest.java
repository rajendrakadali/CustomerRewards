package com.sravani.cr.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sravani.cr.model.Transaction;
import com.sravani.cr.repository.OrderRepository;
@ExtendWith(SpringExtension.class)
public class OrderServiceTest {
	
	@InjectMocks
	private OrderService orderService;
	
	@Mock
	private OrderRepository orderRepository;
	
	
	
	@Test
	public void saveWithoutRewards() {
		Transaction orderToSave = new Transaction(null,20.21f,null,null,1);
		Transaction orderToReturn = new Transaction(1,20.21f,0,null,1);
		when(orderRepository.save(any())).thenReturn(orderToReturn);
		orderService.save(orderToSave);
	}
	
	@Test
	public void saveWithRewards100() {
		Transaction orderToSave1 = new Transaction(null,100.0f,null,null,1);
		Transaction orderToReturn1 = new Transaction(1,100.0f,50,null,1);
		when(orderRepository.save(any())).thenReturn(orderToReturn1);
		orderService.save(orderToSave1);
	}
	
	@Test
	public void saveWithoutRewards150() {
		Transaction orderToSave2 = new Transaction(null,150.0f,null,null,1);
		Transaction orderToReturn2 = new Transaction(1,150.0f,150,null,1);
		when(orderRepository.save(any())).thenReturn(orderToReturn2);
		orderService.save(orderToSave2);
	}
	@Test
	public void getOrders() {
		Transaction orderToReturn = new Transaction(1,20.21f,0,null,1);
		List<Transaction>  orders = Arrays.asList(orderToReturn);
		when(orderRepository.findAll()).thenReturn(orders);
		orderService.getOrders();
	}
	@Test
	public void getOrderByTransId() {
		Transaction orderToReturn = new Transaction(1,20.21f,0,null,1);
		Optional<Transaction> order = Optional.of(orderToReturn);
		when(orderRepository.findById(anyInt())).thenReturn(order);
		orderService.getOrderByTransId(1);
	}
	
	@Test
	public void getOrdersByCustId() {
		Transaction orderToReturn = new Transaction(1,20.21f,0,null,1);
		List<Transaction>  orders = Arrays.asList(orderToReturn);
		when(orderRepository.findOrdersByCustId(anyInt())).thenReturn(orders);
		orderService.getOrdersByCustId(1);
	}
}
