package it.beije.herse.restcontroller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.entity.Order;
import it.beije.herse.entity.User;
import it.beije.herse.service.OrderService;
import it.beije.herse.service.UserService;


@RestController
@RequestMapping("api")
public class OrderRestController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping(path = "/order/list")
	public List<Order> getOrderList() {
		
		List<Order> orders = orderService.searchOrder(LocalDateTime.of(2020, 10, 1, 0, 0));
		System.out.println("orders : " + orders.size());
		
		return orders;
	}

	@GetMapping("/order/detail/{id}")
	public Order getOrder(@PathVariable("id") Integer id) {

		return orderService.findById(id);

	}

	@PostMapping("/order/insert")
	public Order insert(@RequestBody Order order) {
		System.out.println("order : " + order);
		orderService.save(order);

		return order;
	}

	@PutMapping("/order/update/{id}")
	public Order update(@PathVariable("id") Integer id, @RequestBody Order newOrder) {

		if (newOrder.getId() != null && newOrder.getId().compareTo(id) != 0) 
			throw new RuntimeException("id non corrispondente");

		Order order = orderService.findById(id);
		orderService.updateOrder(order, newOrder);
		orderService.save(order);

		return order;
	}

	@DeleteMapping("/order/delete/{id}")
	public String deleteOrder(@PathVariable("id") Integer id) {

		String fdbk = "impossibile eliminare ordine";
		boolean del = orderService.removeOrder(id);

		if(del) fdbk = "ordine eliminato";

		return fdbk;

	}
}
