package it.beije.herse.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.shop.Carrello;
import it.beije.herse.entity.*;
import it.beije.herse.repository.OrderItemRepository;
import it.beije.herse.repository.OrderRepository;
import it.beije.herse.repository.ProductRepository;
import it.beije.herse.repository.UserRepository;
import it.beije.herse.service.OrderService;
import it.beije.herse.service.ProductService;
import it.beije.herse.service.UserService;

@Controller
public class CarrelloController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@RequestMapping(path="/shop/carrello", method = RequestMethod.GET)
	public String carrelloGet(Model model, HttpSession session) {
		
		System.out.println("sono in carrello get");
		model.addAttribute("user", (User)session.getAttribute("user"));
		Carrello carrello =(Carrello) session.getAttribute("carrello");
		
		if(carrello.getMappa().size()==0)
			model.addAttribute("disabled", "disabled");

		Set<Integer> set = carrello.getMappa().keySet();
		Iterator<Integer> indice = set.iterator();
		double total = 0.0;
		
		List<Product> prodottiCarrello = new ArrayList<>();
		
		
		while(indice.hasNext()) {
			Integer i = indice.next();
			Integer quantita = carrello.getMappa().get(i);
			Product p = productService.findById(i);
			prodottiCarrello.add(p);
			total = p.getPrice() * quantita;
			
		}
		
		model.addAttribute("prodottiCarrello", prodottiCarrello);
		model.addAttribute("total", total);
		
		session.setAttribute("prodottiCarrello", prodottiCarrello);
		session.setAttribute("total", total);
		session.setAttribute("carrello", carrello);
		return "shop/carrello";
	}
	
	
	@RequestMapping(path="/shop/pay", method = RequestMethod.POST)
	public String pay(HttpSession session) {
		
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		
		if(carrello == null)
			System.out.println("carrello è null");
		Set<Integer> set = carrello.getMappa().keySet();
		Iterator<Integer> indice = set.iterator();
		Double total = (Double) session.getAttribute("total");
		
		
		User user = (User) session.getAttribute("user");
		Order order = new Order();
		order.setAmount(total);
		order.setDateTime(LocalDateTime.now());
		order.setUserId(user.getId());
		orderRepository.save(order);
		
		int orderId = order.getId();

		while (indice.hasNext()) {

			Integer i = indice.next();
			Integer quantita = carrello.getMappa().get(i);

			Product p = productService.findById(i);
			p.setQuantity(p.getQuantity()-quantita);
			productRepository.save(p);
			
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(orderId);
			orderItem.setProductId(p.getId());
			orderItem.setSellPrice(p.getPrice());
			orderItem.setQuantity(quantita);
			orderItemRepository.save(orderItem);
		}
		return "shop/pay";
	}
	
	
	@RequestMapping(path="/shop/rimuovi", method = RequestMethod.POST)
	public String rimuovi( Model model, HttpSession session, @RequestParam String idP) {
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		Integer key = Integer.valueOf(idP);
		Integer quantita = carrello.getMappa().get(key);
		Double total = (Double) session.getAttribute("total");
		carrello.getMappa().remove(key);
		session.setAttribute("carrello", carrello);
		List<Product> prodottiCarrello = (List<Product>) session.getAttribute("prodottiCarrello");
		for(int i =0; i<prodottiCarrello.size(); i++) {
			if(prodottiCarrello.get(i).getId() == key) {
				total = total - (prodottiCarrello.get(i).getPrice() * quantita);
				prodottiCarrello.remove(i);
				
				}
		}
		session.setAttribute("total", total);
		return "shop/carrello";
	}

}
