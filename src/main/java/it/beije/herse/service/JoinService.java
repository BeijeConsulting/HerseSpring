package it.beije.herse.service;

import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import it.beije.herse.repository.OrderItemRepository;
import it.beije.herse.repository.OrderRepository;
import it.beije.herse.repository.ProductRepository;

import it.beije.herse.entity.*;

@Service
public class JoinService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired 
	private OrderItemRepository orderItemRepository;
	
	
	public List<OrderItem> elencoOrderOrderItems(){
		return orderItemRepository.elencoOrderOrderItems();
	}
	
	
	

}
