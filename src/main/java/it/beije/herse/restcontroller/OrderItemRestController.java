package it.beije.herse.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import it.beije.herse.service.OrderItemService;
import it.beije.herse.entity.OrderItem;

@RestController
@RequestMapping("api")
public class OrderItemRestController {

	@Autowired
	private OrderItemService orderItemService;
	
	@GetMapping("/orderItem/list")
	public List<OrderItem> getListOrderItem() {
		List<OrderItem> orderItems = orderItemService.findAllOrderItems();
		return orderItems;
	}
	
	@GetMapping("orderItem/{id}")
	public List<OrderItem> listByOrder(@PathVariable("id") Integer id) {
		return orderItemService.findByOrderId(id);
	}
}
