package it.beije.herse.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import it.beije.herse.shop.entity.ShopOrder;
import it.beije.herse.shop.entity.ShopOrderItem;
import it.beije.herse.shop.entity.ShopUser;
import it.beije.herse.shop.repository.ShopOrderItemRepository;
import it.beije.herse.shop.repository.ShopOrderRepository;
import it.beije.herse.shop.repository.ShopUserRepository;

@Service
public class UserMenuService {
	
	@Autowired
	private ShopUserRepository userRepository;
	
	@Autowired
	private ShopOrderRepository orderRepository;
	
	@Autowired
	private ShopOrderItemRepository orderItemRepository;
	
	public ShopUser updateUser(ShopUser loggedUser, ShopUser updatedUser) {
		if(updatedUser.getName()!=null && updatedUser.getName().length()>0) loggedUser.setName(updatedUser.getName());
		if(updatedUser.getSurname()!=null && updatedUser.getSurname().length()>0) loggedUser.setSurname(updatedUser.getSurname());
		if(updatedUser.getEmail()!=null && updatedUser.getEmail().length()>0) loggedUser.setEmail(updatedUser.getEmail());
		if(updatedUser.getPassword()!=null && updatedUser.getPassword().length()>0) loggedUser.setPassword(updatedUser.getPassword());
		
		return userRepository.save(loggedUser);
	}
	
	public Map<ShopOrder, List<ShopOrderItem>> getOrderHistory(ShopUser loggedUser) {
		Integer userId = loggedUser.getId();
		
		List<ShopOrder> orders = orderRepository.findByUserId(userId);
		Map<ShopOrder, List<ShopOrderItem>> orderHistory = new HashMap<ShopOrder, List<ShopOrderItem>>();
		for(ShopOrder o : orders) {
			Integer orderId = o.getId();
			List<ShopOrderItem> items = orderItemRepository.findByOrderId(orderId);
			orderHistory.put(o, items);
		}
		
		return orderHistory;
	}
	
	public Map<ShopOrder, List<ShopOrderItem>> getOrderHistoryJoin(ShopUser loggedUser) {
		Integer userId = loggedUser.getId();
		Map<ShopOrder, List<ShopOrderItem>> orderHistory = new HashMap<ShopOrder, List<ShopOrderItem>>();
		
//		List<ShopOrder> orders = orderRepository.findByUserId(userId);
//		for(ShopOrder o : orders) {
//			Integer orderId = o.getId();
//			List<ShopOrderItem> items = orderItemRepository.findByOrderId(orderId);
//			orderHistory.put(o, items);
//		}
		
		return orderHistory;
	}
}
