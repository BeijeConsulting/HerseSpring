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


import it.beije.herse.entity.Carrello;
import it.beije.herse.entity.Product;
import it.beije.herse.repository.OrderItemRepository;
import it.beije.herse.repository.OrderRepository;
import it.beije.herse.repository.UserRepository;
import it.beije.herse.repository.ProductRepository;
import it.beije.herse.service.OrderItemService;
import it.beije.herse.service.OrderService;
import it.beije.herse.service.ProductService;
import it.beije.herse.service.UserService;
import it.beije.herse.entity.Order;
import it.beije.herse.entity.OrderItem;
import it.beije.herse.entity.User;


@Controller
public class CarrelloController {

	
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private ProductService productService;


	@RequestMapping(path = "/carrello/add_product", method = RequestMethod.POST)


	public String add(HttpSession session, Model model, @RequestParam(required = false) Integer to_add , @RequestParam int how_many) {

		Carrello carrello = (Carrello) session.getAttribute("carrello");

		carrello.addProduct(to_add, how_many);
		System.out.println(how_many * productService.findById(to_add).getPrice());
		carrello.setAmount(how_many * productService.findById(to_add).getPrice());

		System.out.println(carrello.toString());

		model.addAttribute("carrello",carrello);
		model.addAttribute("products",productService.productList());

		List<Integer> lista = new ArrayList<Integer>();


		Set<Integer> set = carrello.getProdotti().keySet();
		Iterator<Integer> indice = set.iterator();


		while (indice.hasNext()) {
			lista.add(indice.next());
		}

		model.addAttribute("lista",lista);

		return "prodotti";



	}

	@RequestMapping(path = "/carrello/edit", method = RequestMethod.POST)


	public String edit(HttpSession session, Model model, @RequestParam(required = false) Integer to_edit , @RequestParam int how_many) {

		Carrello carrello = (Carrello) session.getAttribute("carrello");

		double price = productService.findById(to_edit).getPrice();
		carrello.editProduct(to_edit, how_many, price);
		

		System.out.println(carrello.toString());

		model.addAttribute("carrello",carrello);
		model.addAttribute("products",productService.productList());

		List<Integer> lista = new ArrayList<Integer>();


		Set<Integer> set = carrello.getProdotti().keySet();
		Iterator<Integer> indice = set.iterator();


		while (indice.hasNext()) {
			lista.add(indice.next());
		}

		model.addAttribute("lista",lista);

		return "prodotti";



	}

	@RequestMapping(path = "/carrello/remove_product", method = RequestMethod.POST)


	public String delete(HttpSession session, Model model, @RequestParam(required = false) Integer to_del ) {

		Carrello carrello = (Carrello) session.getAttribute("carrello");

		Integer howMany = (Integer) carrello.getProdotti().get(to_del);
		carrello.removeProduct(to_del);
        carrello.setAmount(carrello.getAmount()- (howMany * productService.findById(to_del).getPrice()));

		System.out.println(carrello.toString());

		model.addAttribute("carrello",carrello);
		model.addAttribute("products",productService.productList());

		List<Integer> lista = new ArrayList<Integer>();


		Set<Integer> set = carrello.getProdotti().keySet();
		Iterator<Integer> indice = set.iterator();


		while (indice.hasNext()) {
			lista.add(indice.next());
		}

		model.addAttribute("lista",lista);

		return "prodotti";



	}
	
	@RequestMapping(path = "pay", method = RequestMethod.POST)
	public String pay(HttpSession session) {

		Carrello carrello = (Carrello) session.getAttribute("carrello");


		Set<Integer> set = carrello.getProdotti().keySet();
		Iterator<Integer> indice = set.iterator();

		// Creo ordine
		Order order = new Order();
		order.setAmount(carrello.getAmount());
		User u = (User) session.getAttribute("user");
		
		
		order.setUserId(u.getId());
		order.setDateTime(LocalDateTime.now());
		orderService.save(order);


		while (indice.hasNext()) {

			Integer i = indice.next();
			Integer quantita = carrello.getProdotti().get(i);

			Product p = productService.findById(i);
			p.setQuantity(p.getQuantity()-quantita);
			// Creo order items
			OrderItem oi = new OrderItem();
			oi.setOrderId(order.getId());
			oi.setProductId(p.getId());
			oi.setSellPrice(p.getPrice());
			oi.setQuantity(quantita);
		    orderItemService.save(oi);
	
		}
        
		
		session.removeAttribute("carrello");

		return "congrats";

	}

}