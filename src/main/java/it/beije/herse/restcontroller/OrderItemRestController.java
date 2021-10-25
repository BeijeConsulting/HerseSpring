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

import it.beije.herse.entity.OrderItem;
import it.beije.herse.service.OrderItemService;

@RestController
@RequestMapping("api")
public class OrderItemRestController {

	@Autowired
	private OrderItemService orderItemService;
	
	
	@GetMapping(path="/orderitem/list")
	public List<OrderItem> getListItem(){
		List<OrderItem> items = orderItemService.findAll();
		
		return items;
	}
	
	@GetMapping(path="/orderitem/detail/{id")
	public OrderItem getOrderItem(@PathVariable("id") Integer id) {
		return orderItemService.findById(id);
	}
	
	@PostMapping("orderitem/insert")
	public OrderItem insert(@RequestBody OrderItem item) {
		
		orderItemService.save(item);
		return item;
		
	}
	
	@PutMapping("/orderitem/upate/{id}")
	public OrderItem update(@PathVariable("id") Integer id, @RequestBody OrderItem newItem) {
		if (newItem.getId()!= null && newItem.getId().compareTo(id)!=0)
			throw new RuntimeException("id non corrispondente");
		OrderItem item = orderItemService.findById(id);
		orderItemService.updateOrderItem(item, newItem);
		orderItemService.save(item);
		
		return item;
	}
	
	@DeleteMapping("orderitem/delete/{id}")
	public boolean deleteOrderItem(@PathVariable("id")Integer id) {
		OrderItem oi = orderItemService.findById(id);
		if(oi==null)
			return false;
		else {
			orderItemService.deleteOrderItem(oi);
			return true;
		}
	}
}
