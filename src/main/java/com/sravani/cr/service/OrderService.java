package com.sravani.cr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sravani.cr.model.Transaction;
import com.sravani.cr.repository.OrderRepository;
import com.sravani.cr.util.RewardsUtil;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;


	public Transaction save(Transaction transaction) {
		int rewardPoints = RewardsUtil.calculateRewardPoints(transaction.getTranAmt());
		transaction.setRewardPoints(rewardPoints);
		Transaction trans = orderRepository.save(transaction);
		return trans;
	}

	public List<Transaction> getOrders() {
		return orderRepository.findAll();
	}

	public Transaction getOrderByTransId(Integer transId) {
		return orderRepository.findById(transId).get();
	}
	
	public List<Transaction> getOrdersByCustId(Integer custId) {
		return orderRepository.findOrdersByCustId(custId);
	}
}
