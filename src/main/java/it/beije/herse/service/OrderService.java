package it.beije.herse.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.entity.Order;
import it.beije.herse.entity.OrderItem;
import it.beije.herse.repository.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;

	public Order findById(Integer id) {
		Optional<Order> o = orderRepository.findById(id);
		Order order = o.isPresent() ? o.get() : null;
		
		System.out.println(order.getItems().size());
		return order;
	}

	public List<Order> searchOrder(LocalDateTime localDateTime) {
//		return orderRepository.findByDateTimeGreaterThan(localDateTime);
		return orderRepository.searchByDateTimeGreaterThan(localDateTime);
//		return orderRepository.listOrdersWithItems();
	}
	
	public List<Order> findAllOrders() {
		return orderRepository.findAll();
	}
	
	public List<Order> findByUserId(Integer id) {
		List<Order> orders = orderRepository.findByUserId(id);
		return orders;
	}
	
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public Order updateOrderItem(Order order, Order newData) {
		BeanUtils.copyProperties(newData, order, new String[] { "id" });
		return order;
	}
	
	public void deleteOrder (Integer id) {
		orderRepository.deleteById(id);
	}
	
}
