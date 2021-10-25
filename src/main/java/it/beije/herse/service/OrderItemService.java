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
	
	
	public List<OrderItem> searchByUser(Integer userId) {
		return orderItemRepository.search(userId);
	}


	public List<OrderItem> findAll() {
		return orderItemRepository.findAll();
	}


	public OrderItem findById(Integer id) {
		Optional <OrderItem> o = orderItemRepository.findById(id);
		return o.isPresent() ? o.get() : null;
	}


	public OrderItem save(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}


	public OrderItem updateOrderItem(OrderItem orderItem, OrderItem newOrderItem) {
		BeanUtils.copyProperties(newOrderItem, orderItem, new String[] {"id"});
		return orderItem;
		
	}


	public Boolean delete(OrderItem OrderItemToDelete) {
		if(OrderItemToDelete != null) {
			orderItemRepository.delete(OrderItemToDelete);
			return true;
		} else {
			return false;
		}
	}


	public List<OrderItem> searchByPriceGreaterThan(Double sellPrice) {
		return orderItemRepository.searchByPriceGreaterThan(sellPrice);
	}


	public List<OrderItem> searchByOrderId(Integer orderId) {
		return orderItemRepository.searchByOrderId(orderId);
	}
}
