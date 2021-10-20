package it.beije.herse.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.entity.Order;
import it.beije.herse.repository.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	
	public List<Order> searchOrder(LocalDateTime localDateTime) {
//		return orderRepository.findByDateTimeGreaterThan(localDateTime);
		return orderRepository.searchByDateTimeGreaterThan(localDateTime);
	}
	
	public List<Order> orderList(){
		
	    return orderRepository.findAll();
	}
	
}
