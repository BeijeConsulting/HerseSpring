package it.beije.herse.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.Carrello;
import it.beije.herse.entity.Product;
import it.beije.herse.entity.User;
import it.beije.herse.service.ProductService;
import it.beije.herse.service.UserService;


@Controller
public class UserController {
/*-----------------------------------------------------------------------------*/
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
/*-----------------------------------------------------------------------------*/
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "login"; // /WEB-INF/views/ + login + .jsp
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String auth(Model model, @RequestParam String email, @RequestParam String password) {
		User user;
		List<Carrello> carrello=null;
		List<Product> products;
		user=userService.findByEmail(email);
		if (user!=null&&user.getEmail().equals(email)) {
			model.addAttribute("user", user);
			products=productService.findAll();
			model.addAttribute("products", products);
			model.addAttribute("carrello", carrello);
			return "product/catalogo";
		} else {
			model.addAttribute("error", "Credenziali errate");
			return "login";
		}
		
	}
/*----------------------------------------------------------------------------------------------------*/
	@RequestMapping(path = "signup", method = RequestMethod.GET)
	public String getInsertPage() {
		return "user/signup";
	}
	
	@RequestMapping(path = "signup", method = RequestMethod.POST)
	public String insert(Model model, @Validated User user,@RequestParam String conf_email, @RequestParam String conf_password) {
		List<User> users;
		List<Product> products;
		users=userService.findAll();
		System.out.println("insert user : " + user);
		if(user.getEmail().equalsIgnoreCase(conf_email)&&user.getPassword().equals(conf_password)) {
			for(User u:users)
				if(u.getEmail().equals(user.getEmail()))
					return "user/signup"; // /WEB-INF/views/ + user/insert_user + .jsp
			model.addAttribute("user", user);
			userService.save(user);
			products=productService.findAll();
			model.addAttribute("products", products);
			return "product/catalogo"; // /WEB-INF/views/ + product/insert_user + .jsp
		}else {
			return "user/signup"; // /WEB-INF/views/ + user/insert_user + .jsp
		}
		
	}
/*----------------------------------------------------------------------------------------------------*/
	}
	

