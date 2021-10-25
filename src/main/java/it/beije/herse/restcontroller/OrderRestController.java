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
	
	@PostMapping("/order/insert")
	public Order insert(@RequestBody Order order) {
		return orderService.save(order);
	}
	
	@PutMapping("/order/update/{id}")
	public Order update(@PathVariable("id") Integer id, @RequestBody Order newOrder) {
		
		if(newOrder.getId() != null && newOrder.getId().compareTo(id) != 0)
			throw new RuntimeException("id non corrispondente");
		
		Order order = orderService.findById(id);
		orderService.updateOrder(order, newOrder);
		
		return orderService.save(order);
		
	}
	
	@DeleteMapping("/order/delete/{id}")
	public void insert(@PathVariable("id") Integer id) {	
		orderService.deleteOrder(orderService.findById(id));
	}

}
