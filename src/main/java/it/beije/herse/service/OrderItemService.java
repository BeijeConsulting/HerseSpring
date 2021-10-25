package it.beije.herse.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.entity.Order;
import it.beije.herse.entity.OrderItem;
import it.beije.herse.entity.User;
import it.beije.herse.repository.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	public OrderItem findById(Integer id) {
		Optional<OrderItem> o = orderItemRepository.findById(id);
		OrderItem orderItem = o.isPresent() ? o.get() : null;

		return orderItem;
	}
	

	public List<OrderItem> orderItemList(){

		return orderItemRepository.findAll();
	}

	public OrderItem save(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}

	public OrderItem updateOrderItem(OrderItem orderItem, OrderItem newOrderItem) {

		BeanUtils.copyProperties(newOrderItem, orderItem, new String[]{"id"});

		return orderItem;

	}

	public boolean removeOrderItem(Integer id) {

		if(orderItemRepository.findById(id)!= null) {

			OrderItem o = orderItemRepository.findById(id).get();
			orderItemRepository.delete(o);

			return true;

		} else return false;
	}
}


