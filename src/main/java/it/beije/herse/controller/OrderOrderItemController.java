package it.beije.herse.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.Order;
import it.beije.herse.entity.OrderItem;
import it.beije.herse.service.OrderItemService;
import it.beije.herse.service.OrderService;

@Controller
public class OrderOrderItemController {
	@Autowired
	private OrderItemService orderItemService;
	
	@GetMapping(path = "/orderitem")
	public String orderItem() {
		System.out.println("sono in orderitem get");
		return "orderitem"; 
	}
	
	@GetMapping(path = "/entra")
	public String entra() {
		System.out.println("sono in entra get");
		return "entradentro"; 
	}
	
	@RequestMapping(path = "/orderitem", method = RequestMethod.POST)
	public String showOrderItem(HttpSession session, Model model, @RequestParam String id) {
		int userId = Integer.parseInt(id);
		List<OrderItem> o = orderItemService.searchByUser(userId);
		System.out.println(o);
		model.addAttribute("utente", id);
		model.addAttribute("o", o);
		return "orderitem";
	}
}
