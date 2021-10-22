package it.beije.herse.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.repository.OrderItemRepository;
import it.beije.herse.entity.OrderItem;

@Service
public class OrderItemService {

	@Autowired
	OrderItemRepository orderItemRepository;
	
	public List<OrderItem> findAllOrderItems() {
		List<OrderItem> list = orderItemRepository.findAll();
		return list;
	}
	
	public List<OrderItem> findByOrderId(Integer id) {
		List<OrderItem> listByOrder = orderItemRepository.findByOrderId(id);
		return listByOrder;
	}
	
	public List<OrderItem> findByProductId(Integer id) {
		List<OrderItem> listByProduct = orderItemRepository.findByProductId(id);
		return listByProduct;
	}
	
	public OrderItem saveOrderItem(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}
	
	public OrderItem updateOrderItem(OrderItem orderItem, OrderItem newData) {
		BeanUtils.copyProperties(newData, orderItem, new String[] { "id" });
		return orderItem;
	}
	
	public void deleteOrderItem(Integer id) {
		orderItemRepository.deleteById(id);
	}
}
