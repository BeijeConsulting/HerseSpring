package it.beije.herse.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import it.beije.herse.entity.Product;
import it.beije.herse.repository.OrderItemRepository;
import it.beije.herse.repository.OrderRepository;
import it.beije.herse.repository.UserRepository;
import it.beije.herse.service.OrderService;
import it.beije.herse.service.ProductService;


@Controller
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	

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
			int userId = (int) session.getAttribute("userId");
			Double total = 0.0;
			for(Integer key: map.keySet()) {
				Product p = productService.findById(key);
				if (p != null) {
					int q = (int) map.get(key);
					total = q * p.getPrice();
				}
			}
			Order order = new Order();
			order.setUserId(userId);
			order.setAmount(total);
			order.setDateTime(LocalDateTime.now());
			
			System.out.println("Order PRE: " + order);
			orderRepository.save(order);
			System.out.println("Order POST: " + order);
			model.addAttribute("FinalOrder", order);
			
			List<OrderItem> orders = new ArrayList<OrderItem>();
			
			for(Integer key: map.keySet()) {
				Product p = productService.findById(key);
				if (p != null) {
					OrderItem orderItem = new OrderItem();
					orderItem.setOrderId(order.getId());
					orderItem.setProductId(p.getId());
					orderItem.setQuantity((Integer) map.get(key));
					orderItem.setSellPrice(p.getPrice());
					orderItemRepository.save(orderItem);
					orders.add(orderItem);
					System.out.println("OrderItem: " + orderItem);
				}
			}
			System.out.println("orders" + orders);
			model.addAttribute("ordersToPrint", orders);
		}
		return "order/show";
		
	}
	
	@RequestMapping(path = "/order/detail", method = RequestMethod.GET)
	public String getOrderDetail(Model model, @RequestParam Integer id) {
		
		Order order = orderService.findById(id);
		System.out.println(order);
		model.addAttribute("order", order);
		
		return "order/detail";
	}

}
