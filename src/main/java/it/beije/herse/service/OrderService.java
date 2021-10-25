package it.beije.herse.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.entity.Order;
import it.beije.herse.entity.User;
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
		
	//	return orderRepository.findByDateTimeGreaterThan(localDateTime);
		return orderRepository.searchByDateTimeGreaterThan(localDateTime);
		// return orderRepository.listOrdersWithItems();
	}
	
	public List<Order> orderList(){
		
	    return orderRepository.findAll();
	}
	
	public Order save(Order order) {
		return orderRepository.save(order);
	}
	
public Order updateOrder(Order order, Order newOrder) {
		
		BeanUtils.copyProperties(newOrder, order, new String[]{"id"});
		
		return order;

	}

public boolean removeOrder(Integer id) {
	
	if(orderRepository.findById(id)!= null) {
	
	  Order o = orderRepository.findById(id).get();
	  orderRepository.delete(o);
	  
		return true;
		
	} else return false;
}
}
