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
import it.beije.herse.entity.OrderItem;
import it.beije.herse.entity.User;
import it.beije.herse.service.OrderItemService;
import it.beije.herse.service.OrderService;
import it.beije.herse.service.UserService;


@RestController
@RequestMapping("api")
public class OrderItemRestController {


	@Autowired
	private OrderItemService orderItemService;

	@GetMapping(path = "/orderitem/list")
	public List<OrderItem> getOrderItemList() {

		return orderItemService.orderItemList();
	}

	@GetMapping("/orderitem/detail/{id}")
	public OrderItem getOrderItem(@PathVariable("id") Integer id) {

		return orderItemService.findById(id);

	}

	@PostMapping("/orderitem/insert")
	public OrderItem insert(@RequestBody OrderItem orderItem) {
		System.out.println("orderItem : " + orderItem);
		orderItemService.save(orderItem);

		return orderItem;
	}

	@PutMapping("/orderitem/update/{id}")
	public OrderItem update(@PathVariable("id") Integer id, @RequestBody OrderItem newOrderItem) {

		if (newOrderItem.getId() != null && newOrderItem.getId().compareTo(id) != 0) 
			throw new RuntimeException("id non corrispondente");

		OrderItem orderItem = orderItemService.findById(id);
		orderItemService.updateOrderItem(orderItem, newOrderItem);
		orderItemService.save(orderItem);

		return orderItem;
	}

	@DeleteMapping("/orderitem/delete/{id}")
	public String deleteOrderItem(@PathVariable("id") Integer id) {

		String fdbk = "impossibile eliminare item";
		boolean del = orderItemService.removeOrderItem(id);

		if(del) fdbk = "item eliminato";

		return fdbk;

	}
}
