package it.beije.herse.restcontroller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
