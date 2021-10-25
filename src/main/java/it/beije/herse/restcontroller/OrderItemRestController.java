package it.beije.herse.restcontroller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.entity.OrderItem;
import it.beije.herse.service.OrderItemService;

@RestController
@RequestMapping("api")
public class OrderItemRestController {
	
	@Autowired
	private OrderItemService orderItemService;

	@GetMapping("/orderitem/list/{id}")
	public List<OrderItem> getListOrderItem (@PathVariable("id") Integer orderId) {
		return orderItemService.findByOrderId(orderId);
	}
	
	@PostMapping("/orderitem/insert")
	public OrderItem insert (@RequestBody OrderItem item) {
		return orderItemService.save(item);
	}
	
	@PutMapping("/orderitem/update/{id}")
	public OrderItem update (@PathVariable("id") Integer id, @RequestBody OrderItem newItem) {
		
		if(newItem.getId() != null && newItem.getId().compareTo(id) != 0)
			throw new RuntimeException("id non corrispondente");
		
		OrderItem item = orderItemService.findById(id);
		orderItemService.updateOrderItem(item, newItem);
		
		return orderItemService.save(item);
		
	}
	
	@DeleteMapping("/orderitem/delete/{id}")
	public void delete (@PathVariable("id") Integer id) {
		orderItemService.deleteOrderItem(orderItemService.findById(id));
	}
	
}
