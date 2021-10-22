package it.beije.herse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.beije.herse.entity.User;
import it.beije.herse.repository.UserRepository;
import it.beije.herse.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "login"; // /WEB-INF/views/ + login + .jsp
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String auth(Model model, @RequestParam(required = false) String username, @RequestParam String password) {
		System.out.println("sono in auth(...)");
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		
		if (username.equals("Pippo")) {
			model.addAttribute("username", username);
			return "benvenuti";
		} else {
			model.addAttribute("error", "Credenziali errate");
			return "login";
		}
	}
		
	@RequestMapping(path = "/user/insert", method = RequestMethod.GET)
	public String getInsertPage() {
		return "user/insert_user";
	}
	
	@RequestMapping(path = "/user/insert", method = RequestMethod.POST)
	public String insert(Model model, @Validated User user
//			@RequestParam String username, @RequestParam String password,
//			@RequestParam String firstName, @RequestParam String lastName
			) {
		System.out.println("insert user : " + user);
		userRepository.save(user);
		System.out.println("after save : " + user);
		model.addAttribute("message", "User " + user.getEmail() + " added");
		
		return "user/insert_user"; // /WEB-INF/views/ + user/insert_user + .jsp
	}

	@RequestMapping(path = "/user/list", method = RequestMethod.GET)
	public String getListUsers(Model model, @RequestParam(required = false) String name) {
		
		List<User> users = userService.searchByName(name);
		if (name != null && name.length() > 0) {
			users = userRepository.findByName(name);
		} else {
			users = userRepository.findAll();			
		}
		model.addAttribute("users", users);

		System.out.println(userRepository.listIds().size());
		
		return "user/list";
	}
	

}
