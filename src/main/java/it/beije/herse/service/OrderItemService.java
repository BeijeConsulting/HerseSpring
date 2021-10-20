package it.beije.herse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.entity.OrderItem;
import it.beije.herse.repository.OrderItemRepository;


@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	
	public List<OrderItem> search(Integer userId) {
		return orderItemRepository.search(userId);
	}
}
