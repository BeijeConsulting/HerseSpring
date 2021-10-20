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
import it.beije.herse.entity.User;
import it.beije.herse.service.OrderService;


@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(path = "/order/show")
	public String catalogo() {
		System.out.println("sono in order/show get");
		return "order/show"; 
	}

	@RequestMapping(path = "/order/list", method = RequestMethod.GET)
	public String getOrderList(Model model, @RequestParam(required = false) String name) {
		
		List<Order> orders = orderService.searchOrder(LocalDateTime.of(2021, 10, 1, 0, 0));
		model.addAttribute("orders", orders);
		
		return "order/list";
	}

	@RequestMapping(path = "/order/show", method = RequestMethod.POST)
	public String showOrder(HttpSession session, Model model) {
		
		HashMap<Integer, Object> map = (HashMap<Integer, Object>) session.getAttribute("map");
		
		if(map.isEmpty()) {
			session.setAttribute("noArticoli", "Aggiungi articoli al tuo carrello per proseguire");
			return"carrello/riepilogo";
		} else {
			int userId = (int) session.getAttribute(null);
		}
		return "order/show";
		
	}
	
}
