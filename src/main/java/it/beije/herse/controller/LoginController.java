package it.beije.herse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.OrderItem;
import it.beije.herse.entity.Product;
import it.beije.herse.entity.User;
import it.beije.herse.repository.ProductRepository;
import it.beije.herse.repository.UserRepository;
import it.beije.herse.service.JoinService;
import it.beije.herse.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductRepository productService;
	
	@Autowired
	private JoinService joinService;
	
	
	@RequestMapping(path="/shop/login",  method = RequestMethod.GET)
	public String accessoGet() {
		System.out.println("Sono in accessoGet");
		return "shop/accesso_ecommerce";
	}
	
	@RequestMapping(path="/shop/login",  method = RequestMethod.POST)
	public String accesso(Model model, @Validated User user) {
		System.out.println("Sono in accessoPost");
		String email = user.getEmail();
		String password = user.getPassword();
		System.out.println("email: " + email);
		User utente = userService.findByEmailAndPAssword(email, password);
//		System.out.println(utente);
	
		
		
		if(utente!=null) {
			model.addAttribute("user", utente);
			List<Product> prodotti = productService.findAll();
	//		System.out.println(prodotti);
			model.addAttribute("prodotti", prodotti);
			return "./shop/catalogo";
		}
		
		else {
			model.addAttribute("error", "Credenziali errate");
			return "./shop/accesso_ecommerce";
		}
		
		
	}	
	
	@RequestMapping(path="/shop/iscriviti",  method = RequestMethod.GET)
	public String iscrivitiGet() {
		System.out.println("sono in iscrivitiGet");
		return "./shop/iscriviti";
	}
	
	
	@RequestMapping(path="/shop/iscriviti",  method = RequestMethod.POST)
	public String iscrivitiPost(Model model, @Validated User user) {
		
		
		userRepository.save(user);
		return "./shop/accesso_ecommerce";
	}
	
	@RequestMapping(path="/shop/elenco",  method = RequestMethod.GET )
	public String elenco(Model model) {
		
		List<OrderItem> oi = joinService.elencoOrderOrderItems();
		System.out.println("oi " + oi);
		model.addAttribute("elenco", oi);
		return "./shop/elenco";
	}

}
