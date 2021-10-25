package it.beije.herse.my.restcontroller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.bean.OrderItems;
import it.beije.herse.my.service.MyOrderItemsService;

@RestController
@RequestMapping("api")
public class MyOrderItemsRestController {
	
	@Autowired
	private MyOrderItemsService myOrderItemsService;
	
	@GetMapping("/orderItems/details/{id}")
	public OrderItems getOrderItems(@PathVariable("id") Integer id){
		OrderItems oi = myOrderItemsService.findById(id);
		
		return oi;
	}
	
	@PostMapping("/orderItems/insert")
	public OrderItems insert(@RequestBody OrderItems orderItem) {
		System.out.println("OrderItem: " + orderItem);
		
		myOrderItemsService.save(orderItem);
		
		return orderItem;	
	}
	
	@PutMapping("/orderItems/update/{id}")
	public OrderItems update(@PathVariable("id") Integer id,@RequestBody OrderItems orderItem) {
		if(orderItem.getId() != null && orderItem.getId() != 0) {
			throw new RuntimeException("id non corrispondente");
		}
		OrderItems oi = myOrderItemsService.findById(id);
		myOrderItemsService.updateOrder(oi, orderItem);
		myOrderItemsService.save(oi);
		
		return oi;
	}
	
	@PutMapping("/orderItems/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		OrderItems orderItems = myOrderItemsService.findById(id);
		String response;
		if(orderItems != null) {
			response = myOrderItemsService.deleteOrder(orderItems) ? "deleted" : "not deleted";
			System.out.println(response);
		}else {
			System.out.println("OrderItem not found");
		}
	}
}
