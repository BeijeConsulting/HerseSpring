package it.beije.herse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.beije.herse.entity.Order;
import it.beije.herse.entity.OrderItem;
import it.beije.herse.entity.User;
import it.beije.herse.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public User findById(Integer id) {
		Optional<User> u = userRepository.findById(id);
		return u.isPresent() ? u.get() : null;
	}

	public List<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public List<User> findByName(String name) {
		return userRepository.findByName(name);
	}
	
	public List<User> findByNameAndSurname(String name, String surname) {
		return userRepository.findByNameAndSurname(name, surname);
	}

	public List<User> searchByName(String name) {
		List<User> users;
		if (name != null && name.length() > 0) {
			users = userRepository.findByName(name);
		} else {
			users = userRepository.findAll();
		}

		return users;
	}

	public User findByEmailAndPassword(String email, String password) {
		Optional<User> user = userRepository.findByEmailAndPassword(email, password);
		return user.isPresent() ? user.get() : null;
	}

	public User updateUser(User user, User newData) {
		BeanUtils.copyProperties(newData, user, new String[] { "id" });
		return user;
	}
	
	public void deleteUser(Integer id) {
		List<Order> ordersUser = orderService.findByUserId(id);
		for (Order o : ordersUser) {
			Integer orderId = o.getId();
			List<OrderItem> listOI = orderItemService.findByOrderId(orderId);
			for (OrderItem oi : listOI) {
				orderItemService.deleteOrderItem(oi.getId());
			}
			orderService.deleteOrder(o.getId());
		}
		userRepository.deleteById(id);
		System.out.println("user deleted : " + id);
	}

}
