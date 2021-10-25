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
import it.beije.herse.entity.Product;
import it.beije.herse.service.OrderItemService;

@RestController
@RequestMapping("api")
public class OrderItemRestController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	@GetMapping("orderitem/list")
	public List<OrderItem> getOrderItemList(){
		return orderItemService.findAll();
	}
	
	@PostMapping("orderitem/insert")
	public OrderItem insert(@RequestBody OrderItem orderItem) {
		System.out.println("orderItem: " + orderItem);
		return orderItemService.save(orderItem);
	}
	
	@PutMapping("orderitem/update/{id}")
	public OrderItem update(@PathVariable("id") Integer id, @RequestBody OrderItem newOrderItem) {
		OrderItem orderItem = orderItemService.findById(id);
		orderItemService.updateOrderItem(orderItem, newOrderItem);
		return orderItemService.save(orderItem);
	}
	
	@DeleteMapping("orderitem/delete/{id}")
	public Boolean delete(@PathVariable("id") Integer id) {
		OrderItem orderToDelete = orderItemService.findById(id);
		return orderItemService.delete(orderToDelete);
	}
	
	@GetMapping("oderitem/search/user/{id}")
	public List<OrderItem> searchByUserId(@PathVariable("id") Integer id){
		return orderItemService.searchByUser(id);
	}
	
	@GetMapping("orderitem/detail/{id}")
	public OrderItem searchById(@PathVariable("id") Integer id) {
		return orderItemService.findById(id);
	}
	
	@GetMapping("orderitem/search/price/greater/{sellPrice}")
	public List<OrderItem> searchByPriceGreaterThan(@PathVariable("sellPrice") Double sellPrice ){
		return orderItemService.searchByPriceGreaterThan(sellPrice);
	}
	
	@GetMapping("orderitem/order/{orderId}")
	public List<OrderItem> searchUser(@PathVariable("orderId") Integer orderId){
		return orderItemService.searchByOrderId(orderId);
	}
}
