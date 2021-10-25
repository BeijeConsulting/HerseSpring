package it.beije.herse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.entity.OrderItem;
import it.beije.herse.repository.OrderItemRepository;

@Service
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public OrderItem save(OrderItem item) {
		return orderItemRepository.save(item);
	}
	
	public List<OrderItem> findAll(){
		return orderItemRepository.findAll();
	}
	
	public OrderItem findById(Integer id) {
		Optional<OrderItem> oi = orderItemRepository.findById(id);
		return oi.isPresent() ? oi.get() : null;
	}
	
	public OrderItem updateOrderItem(OrderItem item, OrderItem newItem) {
		
		BeanUtils.copyProperties(newItem, item, new String[] {"id"});
		return item;
	}
	
	public void deleteOrderItem(OrderItem item) {
		orderItemRepository.delete(item);
	}

}
