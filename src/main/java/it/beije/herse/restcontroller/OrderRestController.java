package it.beije.herse.restcontroller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.entity.Order;
import it.beije.herse.entity.User;
import it.beije.herse.service.OrderService;


@RestController
@RequestMapping("api")
public class OrderRestController {

	@Autowired
	private OrderService orderService;

	@GetMapping(path = "/order/list")
	public List<Order> getOrderList() {

		List<Order> orders = orderService.searchOrder(LocalDateTime.of(2021, 10, 1, 0, 0));
		System.out.println("orders : " + orders.size());

		return orders;
	}

	@GetMapping("/order/detail/{id}")
	public Order getOrder(@PathVariable("id") Integer id) {

		return orderService.findById(id);
	}

	@PostMapping("/order/insert")
	public Order insert(@RequestBody Order order) {
		orderService.save(order);
		return order;
	}

	@PutMapping("/order/update/{id}")
	public Order update(@PathVariable("id") Integer id, @RequestBody Order order) {
		if (order.getId() != null && order.getId().compareTo(id) != 0) 
			throw new RuntimeException("id non corrispondente");

		Order user = orderService.findById(id);
		orderService.updateOrder(user, order);
		orderService.save(user);

		return order;
	}

	@DeleteMapping("/user/delete/{id}")
}




