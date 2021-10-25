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
	
	@PostMapping("/order/insert")
	public Order insert(@RequestBody Order order) {
		System.out.println("orer: " + order);
		return orderService.save(order);
	}
	
	@PutMapping("order/update/{id}")
	public Order order(@PathVariable("id") Integer id, @RequestBody Order newOrder) {
		Order order = orderService.findById(id);
		order = orderService.upadateOrder(order, newOrder);
		return orderService.save(order);
	}
	
	@DeleteMapping("order/delete/{id}")
	public Boolean delete(@PathVariable("id") Integer id) {
		Order orderToDelete = orderService.findById(id);
		return orderService.delete(orderToDelete);
	}
	
	@GetMapping("order/search/userid/amount/{userId}/{amount}")
	public List<Order> searchByUserIdAndAmount(@PathVariable("userId") Integer userId, @PathVariable("amount") Double amount){
		return orderService.searchByUserIdAndAmount(userId, amount);
	}

}
