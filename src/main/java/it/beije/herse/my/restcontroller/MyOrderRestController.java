package it.beije.herse.my.restcontroller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.beije.herse.my.service.MyOrderService;
import it.beije.herse.bean.Orders;

@RestController
@RequestMapping("api")
public class MyOrderRestController {
	
	@Autowired
	private MyOrderService myOrderService;
	
	@GetMapping("/orders/detail/{userId}")
	public List<Orders> getOrders(@PathVariable("userId") Integer id) {
		List<Orders> ordiniUtente = myOrderService.findByUserId(id);
		if(ordiniUtente.size() == 0) {
			System.out.println("non ci sono ordini");
			return null;
		}
		return ordiniUtente;
	}
	
	@GetMapping("/orders/detail")
	public List<Orders> getOrder() {
		List<Orders> order = myOrderService.findAll();
		if(order.size() == 0) {
			System.out.println("non ci sono ordini");
			return null;
		}
		return order;
	}
	
	@PostMapping("/orders/insert")
	public Orders insert(@RequestBody Orders order) {
		System.out.println("Order: " + order);
		if(order.getDateTimeAsString() == null && order.getDateTime() == null) {
			order.setDateTime(LocalDateTime.now());
			order.setDateTime(LocalDateTime.now().toString());
		}
		myOrderService.save(order);
		
		return order;
	}
	
	@PutMapping("/orders/update/{id}")
	public Orders update(@PathVariable("id") Integer id, @RequestBody Orders updateOrder) {
		if(updateOrder.getId() != null && updateOrder.getId() != 0) {
			throw new RuntimeException("id non corrispondente");
		}
		Orders order = myOrderService.findById(id);
		myOrderService.updateOrder(order, updateOrder);
		myOrderService.save(order);
		
		return order;
	}
	
	@PutMapping("/orders/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		Orders orders = myOrderService.findById(id);
		String response;
		if(orders != null) {
			response = myOrderService.deleteOrder(orders) ? "deleted" : "not deleted";
			System.out.println(response);
		}else {
			System.out.println("Order not found");
		}
	}

}
