package it.beije.herse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.repository.OrderItemRepository;
import it.beije.herse.entity.OrderItem;

@Service
public class OrderItemService {

	@Autowired
	OrderItemRepository orderItemRepository;
	
	public List<OrderItem> findAll() {
		List<OrderItem> list = orderItemRepository.findAll();
		return list;
	}
	
	public List<OrderItem> findByOrderId(Integer id) {
		List<OrderItem> listByOrder = orderItemRepository.findByOrderId(id);
		return listByOrder;
	}
}
