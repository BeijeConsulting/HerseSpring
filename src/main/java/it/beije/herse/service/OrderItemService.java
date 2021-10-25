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
	
	public OrderItem findById(Integer id) {
		
		Optional<OrderItem> i = orderItemRepository.findById(id);
		OrderItem item = i.isPresent() ? i.get() : null;

		return item;
	}
	
	public List<OrderItem> findByOrderId (Integer id) {
		return orderItemRepository.findByOrderId(id);
	}
	
	public OrderItem save(OrderItem item) {
		return orderItemRepository.save(item);
	}
	
	public OrderItem updateOrderItem(OrderItem item, OrderItem newData) {
		
		BeanUtils.copyProperties(newData, item, new String[]{"id"});
		
		return item;
		
	}
	
	public void deleteOrderItem(OrderItem item) {
		orderItemRepository.delete(item);
	}
	
}
