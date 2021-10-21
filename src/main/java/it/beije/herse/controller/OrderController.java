package it.beije.herse.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.Order;
import it.beije.herse.service.OrderService;


@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(path = "/order/list", method = RequestMethod.GET)
	public String getOrderList(Model model, @RequestParam(required = false) String name) {
		
		List<Order> orders = orderService.searchOrder(LocalDateTime.of(2021, 10, 1, 0, 0));
		model.addAttribute("orders", orders);
		
		return "order/list";
	}
	

	
	
    

	@RequestMapping(path = "/order/detail", method = RequestMethod.GET)
	public String getOrderDetail(Model model, @RequestParam Integer id) {
		
		Order order = orderService.findById(id);
		System.out.println(order);
		model.addAttribute("order", order);
		
		return "order/detail";
	}

}
