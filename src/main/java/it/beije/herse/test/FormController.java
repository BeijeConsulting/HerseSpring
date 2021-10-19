package it.beije.herse.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.Product;
import it.beije.herse.User;

@Controller
public class FormController {
	
	@RequestMapping(path="/", method = RequestMethod.GET)
	public String index() {
		// index
		return "login/login";
	}
	
	@PostMapping(path = "/index")
	public String backToIndex() {
		return "login/login";
	}
	
	@RequestMapping(path="/login/logIn", method = RequestMethod.POST)
	public String login(Model model, @Validated User user) {
		
		System.out.println("USER: "+user);
		
		String username = user.getUsername();
		String password = user.getPassword();
		model.addAttribute("user", user);
		
		if(username.equalsIgnoreCase("nv@gmail.com") && password.equals("nv")) {
			return "login/insert";
		}
		
		return "login/retry";
	}
//	public String login(Model model, @RequestParam String email, @RequestParam String password) {
//		
//		System.out.println("EMAIL: "+email);
//		model.addAttribute("email", email);
//		
//		System.out.println("PASSWORD: "+password);
//		model.addAttribute("password", password);
//		if(email.equalsIgnoreCase("nv@gmail.com") && password.equals("nv")) {
//			return "insert";
//		}
//		
//		return "retry";
//	}
	
	@RequestMapping(path="login/retry", method = RequestMethod.POST)
	public String retry(Model model, @RequestParam String retryAction, @Validated User user) {
		
		model.addAttribute("user", user);
		if(retryAction.equalsIgnoreCase("back")) {
			model.addAttribute("loginMessage", "EMAIL AND PASSWORD NOT FOUND, RETRY");
			return "login/login";
		}
		
		return "login/insert";
	}
	
	@RequestMapping(path="login/insert", method = RequestMethod.POST)
	public String insert(Model model, @Validated User user) {
		System.out.println("USER: "+user);
		
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		
		model.addAttribute("firstName", firstName);
		model.addAttribute("lastName", lastName);
		
		model.addAttribute("products", getProducts());
		
		return "user/menu";
	}
//	public String insert(Model model, @RequestParam String name, @RequestParam String surname) {
//		
//		if(name.length()>0 && surname.length()>0) {
//			model.addAttribute("name", name);
//			model.addAttribute("surname", surname);
//		}
//		
//		return "result";
//	}
	
	private List<Product> getProducts(){
		List<Product> products = new ArrayList<Product>();
		
		Product p1 = new Product();
		p1.setId(1);
		p1.setName("OCA Study Guide");
		p1.setDescription("Java Guide");
		p1.setPrice(9.99);
		p1.setQuantity(99);
		products.add(p1);
		
		Product p2 = new Product();
		p2.setId(2);
		p2.setName("Design Pattern");
		p2.setDescription("Java Guide");
		p2.setPrice(19.99);
		p2.setQuantity(99);
		products.add(p2);
		
		Product p3 = new Product();
		p3.setId(3);
		p3.setName("OCA/OCP Practice Test");
		p3.setDescription("Test Book");
		p3.setPrice(14.95);
		p3.setQuantity(99);
		products.add(p3);
		
		return products;
	}
}
