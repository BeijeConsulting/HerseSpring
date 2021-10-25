package it.beije.herse.my.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
