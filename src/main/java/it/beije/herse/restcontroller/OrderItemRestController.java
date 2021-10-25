package it.beije.herse.restcontroller;

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
	
	@PostMapping("/orderItem/insert")
	public OrderItem insert(@RequestBody OrderItem orderItem) {
		System.out.println("orderItem : " + orderItem);
		orderItemService.saveOrderItem(orderItem);
		
		return orderItem;
	}

	@PutMapping("/orderItem/update/{id}")
	public OrderItem update(@PathVariable("id") Integer id, @RequestBody OrderItem newOrderItem) {
		
		if (newOrderItem.getId() != null && newOrderItem.getId().compareTo(id) != 0) 
			throw new RuntimeException("id non corrispondente");
		
		OrderItem orderItem = orderItemService.findById(id);
		orderItemService.updateOrderItem(orderItem, newOrderItem);
		orderItemService.saveOrderItem(orderItem);
		
		return orderItem;
	}
	
	@DeleteMapping("/orderItem/delete/{id}") 
	public void delete(@PathVariable("id") Integer id) {
		orderItemService.deleteOrderItem(id);
	}
}
