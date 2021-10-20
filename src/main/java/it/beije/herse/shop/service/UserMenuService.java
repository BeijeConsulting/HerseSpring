package it.beije.herse.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import it.beije.herse.shop.entity.Order;
import it.beije.herse.shop.entity.OrderItem;
import it.beije.herse.shop.entity.User;
import it.beije.herse.shop.repository.OrderItemRepository;
import it.beije.herse.shop.repository.OrderRepository;
import it.beije.herse.shop.repository.UserRepository;

@Service
public class UserMenuService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	public User updateUser(User loggedUser, User updatedUser) {
		if(updatedUser.getName()!=null && updatedUser.getName().length()>0) loggedUser.setName(updatedUser.getName());
		if(updatedUser.getSurname()!=null && updatedUser.getSurname().length()>0) loggedUser.setSurname(updatedUser.getSurname());
		if(updatedUser.getEmail()!=null && updatedUser.getEmail().length()>0) loggedUser.setEmail(updatedUser.getEmail());
		if(updatedUser.getPassword()!=null && updatedUser.getPassword().length()>0) loggedUser.setPassword(updatedUser.getPassword());
		
		return userRepository.save(loggedUser);
	}
	
	public Map<Order, List<OrderItem>> getOrderHistory(User loggedUser) {
		Integer userId = loggedUser.getId();
		
		List<Order> orders = orderRepository.findByUserId(userId);
		Map<Order, List<OrderItem>> orderHistory = new HashMap<Order, List<OrderItem>>();
		for(Order o : orders) {
			Integer orderId = o.getId();
			List<OrderItem> items = orderItemRepository.findByOrderId(orderId);
			orderHistory.put(o, items);
		}
		
		return orderHistory;
	}
	
	public Map<Order, List<OrderItem>> getOrderHistoryJoin(User loggedUser) {
		Integer userId = loggedUser.getId();
		Map<Order, List<OrderItem>> orderHistory = new HashMap<Order, List<OrderItem>>();
		
//		List<Order> orders = orderRepository.findByUserId(userId);
//		for(Order o : orders) {
//			Integer orderId = o.getId();
//			List<OrderItem> items = orderItemRepository.findByOrderId(orderId);
//			orderHistory.put(o, items);
//		}
		
		return orderHistory;
	}
}
