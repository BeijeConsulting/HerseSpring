package it.beije.herse.controller;

import java.util.List;


import javax.servlet.http.HttpSession;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.User;
import it.beije.herse.repository.UserRepository;

import it.beije.herse.service.ProductService;
import it.beije.herse.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;
	
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login(HttpSession session) {
		
		if(session.getAttribute("user")==null)
		return "home"; // /WEB-INF/views/ + login + .jsp
		
		else return "benvenuti";
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)


	public String auth(HttpSession session, Model model, @RequestParam(required = false) String user, @RequestParam String pass) {
		System.out.println("sono in login post");

        
		User u = userRepository.findByEmail(user);
		String feedback = null;
		
		if(u!=null) {
		
			if(pass.equals(u.getPassword())) {
				
				session.setAttribute("user", user);
				model.addAttribute("products",productService.productList());
				return "benvenuti";
				
			} else {
				
				feedback = "la password non corrisponde";
			}
		
		} else {
			
			feedback = "questo nome utente non esiste";
			
		}
		
		model.addAttribute("feedback", feedback);
		return "home";
		
	}
	
	@RequestMapping(path = "/user/insert", method = RequestMethod.GET)
	public String getInsertPage() {
		return "user/insert_user";
	}
	
	@RequestMapping(path = "/user/insert", method = RequestMethod.POST)
	public String insert(Model model, @Validated User user
//			@RequestParam String email, @RequestParam String password,
//			@RequestParam String surname, @RequestParam String name
			) {

	 	User u = (User) userRepository.findByEmail(user.getEmail());
		
	 	if(u!=null) {
	 		
	 		model.addAttribute("already", user.getEmail() + "</span> email <span style='color:orange'> already exists");
	 		
	 		return "user/insert_user";
	 	} else {
				
	    System.out.println("insert user : " + user);
		
		userRepository.save(user);
		
		System.out.println("after save : " + user);
		
		model.addAttribute("message", "User " + user.getEmail() + " added");
		
		return "user/insert_user"; // /WEB-INF/views/ + user/insert_user + .jsp
	 	}
	}

	@RequestMapping(path = "/user/list", method = RequestMethod.GET)
	public String getListUsers(Model model, @RequestParam(required = false) String name) {
		
		List<User> users = userService.searchByName(name);
//		if (name != null && name.length() > 0) {
//			users = userRepository.findByName(name);
//		} else {
//			users = userRepository.findAll();			
//		}
		model.addAttribute("users", users);
		
		return "user/list";
	}
	
	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
	    session.removeAttribute("user");
		return "home";
	}

}
